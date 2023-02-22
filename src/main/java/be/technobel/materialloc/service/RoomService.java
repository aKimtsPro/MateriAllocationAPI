package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> findCompatibleRoomsForRequest(Long requestId);

}
