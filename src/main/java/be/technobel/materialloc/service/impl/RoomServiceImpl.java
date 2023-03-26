package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.exceptions.NotFoundException;
import be.technobel.materialloc.models.dto.RoomDTO;
import be.technobel.materialloc.models.entity.Material;
import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.Room;
import be.technobel.materialloc.models.form.RoomForm;
import be.technobel.materialloc.repository.MaterialRepository;
import be.technobel.materialloc.repository.RequestRepository;
import be.technobel.materialloc.repository.RoomRepository;
import be.technobel.materialloc.service.MaterialService;
import be.technobel.materialloc.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RequestRepository requestRepository;
    private final MaterialRepository materialRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RequestRepository requestRepository, MaterialRepository materialRepository) {
        this.roomRepository = roomRepository;
        this.requestRepository = requestRepository;
        this.materialRepository = materialRepository;
    }

    @Override
    // y a bcp mieux / 20
    public List<RoomDTO> findCompatibleRoomsForRequest(Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow( () -> new NotFoundException(Request.class, requestId));

//        String madeByRole = request.getMadeBy().getRole();

        List<Room> potentialRooms = roomRepository.searchRoomForTeacher(
                        request.getNeededCapacity(),
                        request.getDate(),
                        request.getBeginTime(),
                        request.getEndTime()
                );

        System.out.println("rooms:" + potentialRooms);

        return potentialRooms.stream()
                .filter( room -> room.getMaterials().containsAll(request.getMaterials()) )
                .map( RoomDTO :: toDto )
                .toList();
    }

    @Override
    public List<RoomDTO> getAll() {
        return roomRepository.findAll().stream()
                .map(RoomDTO::toDto)
                .toList();
    }

    @Override
    public void insertRoom(RoomForm form) {
        Room room = form.toEntity();
        room.setMaterials( new HashSet<>(materialRepository.findAllById(form.getMaterialsId())) );
        roomRepository.save(room);
    }
}
