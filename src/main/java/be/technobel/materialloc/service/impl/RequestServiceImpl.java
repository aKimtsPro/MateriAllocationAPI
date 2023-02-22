package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.models.dto.ReducedRequestDTO;
import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.entity.Status;
import be.technobel.materialloc.models.entity.users.Person;
import be.technobel.materialloc.models.form.RequestForm;
import be.technobel.materialloc.repository.MaterialRepository;
import be.technobel.materialloc.repository.PersonRepository;
import be.technobel.materialloc.repository.RequestRepository;
import be.technobel.materialloc.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final MaterialRepository materialRepository;
    private final PersonRepository personRepository;

    public RequestServiceImpl(RequestRepository requestRepository, MaterialRepository materialRepository, PersonRepository personRepository) {
        this.requestRepository = requestRepository;
        this.materialRepository = materialRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void create(RequestForm form) {

        Request request = form.toEntity();

        // link user
        Person p = personRepository.findByLogin(form.getUserLogin())
                .orElseThrow(); // TODO: préciser

        if( Objects.equals(p.getRole(), "ADMIN") )
            throw new RuntimeException(); // TODO: préciser

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

        requestRepository.save( request );
    }

    @Override
    public List<ReducedRequestDTO> getFutureWithStatus(RequestStatus status){
        return requestRepository.findFutureWithCurrentStatus(status).stream()
                .map( ReducedRequestDTO::toDto )
                .toList();
    }
}
