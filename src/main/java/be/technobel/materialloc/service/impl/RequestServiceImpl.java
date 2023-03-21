package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.exceptions.InvalidRoomException;
import be.technobel.materialloc.exceptions.NotFoundException;
import be.technobel.materialloc.exceptions.RequestStatusException;
import be.technobel.materialloc.models.dto.ReducedRequestDTO;
import be.technobel.materialloc.models.dto.RequestDTO;
import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.entity.Room;
import be.technobel.materialloc.models.entity.Status;
import be.technobel.materialloc.models.entity.users.User;
import be.technobel.materialloc.models.form.RequestForm;
import be.technobel.materialloc.repository.MaterialRepository;
import be.technobel.materialloc.repository.RoomRepository;
import be.technobel.materialloc.repository.UserRepository;
import be.technobel.materialloc.repository.RequestRepository;
import be.technobel.materialloc.service.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final MaterialRepository materialRepository;
    private final UserRepository personRepository;
    private final RoomRepository roomRepository;

    public RequestServiceImpl(RequestRepository requestRepository, MaterialRepository materialRepository, UserRepository personRepository,
                              RoomRepository roomRepository) {
        this.requestRepository = requestRepository;
        this.materialRepository = materialRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void create(RequestForm form) {

        Request request = form.toEntity();

        // link user
        User p = personRepository.findByLogin(form.getUserLogin())
                .orElseThrow( () -> new NotFoundException(User.class, form.getUserLogin()) );

        if( Objects.equals(p.getRole(), "ADMIN") )
            throw new RuntimeException("Shouldn't be ADMIN");

        request.setMadeBy( p );

        // link materials
        request.setMaterials(
                new LinkedHashSet<>(materialRepository.findAllById(form.getMaterialIds()))
        );

        // generate SENT status
        Status status = new Status();
        status.setRequest(request);
        status.setJustification("REQUEST SENT");
        status.setStatus(RequestStatus.PENDING);

        request.getStatusHistory().add(status);
        request.setCurrentStatus(status.getStatus());

        requestRepository.save( request );
    }

    @Override
    public List<ReducedRequestDTO> getFutureWithStatus(RequestStatus status){
        return requestRepository.findFutureWithCurrentStatus(status).stream()
                .map( ReducedRequestDTO::toDto )
                .toList();
    }


    @Override
    public RequestDTO getRequestDetails(Long id) {
        return requestRepository.findById(id)
                .map(RequestDTO::toDto)
                .orElseThrow(() -> new NotFoundException(Request.class, id));
    }

    @Override
    public void refuseRequest(Long id, String justification) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Request.class, id));

        Status currentStatus = request.getStatusHistory().stream()
                .max(Comparator.comparing(Status::getCreatedAt))
                .orElse(null);


        if(
                currentStatus != null &&
                currentStatus.getStatus() != RequestStatus.PENDING &&
                currentStatus.getStatus() != RequestStatus.RELOCATING
        )
            throw new RequestStatusException();

        Status status = new Status();
        status.setJustification(justification != null ? justification : "NO JUSTIFICATION");
        status.setRequest(request);
        status.setStatus(RequestStatus.REFUSED);

        request.getStatusHistory().add(status);
        request.setCurrentStatus(RequestStatus.REFUSED);
        requestRepository.save(request);
    }

    @Override
    public void relocateRequest(Long id, String justification) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Request.class, id));

        Status currentStatus = request.getStatusHistory().stream()
                .max(Comparator.comparing(Status::getCreatedAt))
                .orElse(null);

        if( currentStatus != null && currentStatus.getStatus() != RequestStatus.ACCEPTED )
            throw new RequestStatusException();

        Status status = new Status();
        status.setJustification(justification != null ? justification : "NO JUSTIFICATION");
        status.setRequest(request);
        status.setStatus(RequestStatus.RELOCATING);

        request.getStatusHistory().add(status);
        request.setCurrentStatus(RequestStatus.RELOCATING);
        requestRepository.save(request);
    }

    @Override
    @Transactional
    public void cleanRequests(){
        List<Request> toClean = requestRepository.findToCleanBefore(LocalDate.now().plusDays(3));

        toClean.forEach(
                request -> {
                    Status status = new Status();
                    status.setStatus(RequestStatus.REFUSED);
                    status.setChangedBy(null);
                    status.setJustification("REQUEST TIMEOUT");
                    status.setRequest(request);
                    request.getStatusHistory().add(status);
                    request.setCurrentStatus(RequestStatus.REFUSED);
                }
        );

        requestRepository.saveAll(toClean);
    }

    @Override
    public void acceptRequest(Long id, String justification, long roomId) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new NotFoundException(Request.class, id));

        if( request.getCurrentStatus() != RequestStatus.PENDING && request.getCurrentStatus() != RequestStatus.RELOCATING )
            throw new RequestStatusException();

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new NotFoundException(Room.class, roomId));

        request.setRoom(room);

        // Check room compatibility
        throwIfRoomInvalid(room, request);

        Status status = new Status();
        status.setRequest(request);
        status.setChangedBy(null);
        status.setStatus(RequestStatus.ACCEPTED);
        status.setJustification(justification);

        request.getStatusHistory().add(status);
        request.setCurrentStatus(RequestStatus.ACCEPTED);

        requestRepository.save(request);
    }

    private void throwIfRoomInvalid(Room room, Request request){
        boolean inferiorCapacity = room.getCapacity() < request.getNeededCapacity();
        boolean hasConflit = room.getRequests().stream()
                .anyMatch( req -> req.getDate().equals( request.getDate() ) && !( req.getBeginTime().isAfter(req.getEndTime()) || req.getEndTime().isBefore(request.getBeginTime()) ) );
        boolean missingMaterials = !room.getMaterials().containsAll(request.getMaterials());

        List<String> causes = new ArrayList<>();
        if( inferiorCapacity )
            causes.add("not enough capacity");

        if( hasConflit )
            causes.add("this room is already in use for this time frame");

        if( missingMaterials )
            causes.add("this room doesn't have the required materials");

        if( inferiorCapacity || hasConflit || missingMaterials )
            throw new InvalidRoomException(causes);
    }
}
