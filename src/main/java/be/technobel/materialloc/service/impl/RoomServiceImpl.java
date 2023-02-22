package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.exceptions.NotFoundException;
import be.technobel.materialloc.models.dto.RoomDTO;
import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.Room;
import be.technobel.materialloc.repository.RequestRepository;
import be.technobel.materialloc.repository.RoomRepository;
import be.technobel.materialloc.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RequestRepository requestRepository;

    public RoomServiceImpl(RoomRepository roomRepository, RequestRepository requestRepository) {
        this.roomRepository = roomRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    // y a bcp mieux / 20
    public List<RoomDTO> findCompatibleRoomsForRequest(Long requestId) {

        Request request = requestRepository.findById(requestId)
                .orElseThrow( () -> new NotFoundException(Request.class, requestId));

        String madeByRole = request.getMadeBy().getRole();

        List<Room> potentialRooms = roomRepository.searchRoomForTeacher(
                        request.getNeededCapacity(),
                        request.getDate(),
                        request.getBeginTime(),
                        request.getEndTime()
                );

        return potentialRooms.stream()
                .filter(
                        room -> room.getMaterials()
                                .containsAll(request.getMaterials())
                )
                .map( RoomDTO :: toDto )
                .toList();
    }
}
