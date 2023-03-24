package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.RoomDTO;
import be.technobel.materialloc.models.form.RoomForm;

import java.util.List;

public interface RoomService {

    List<RoomDTO> findCompatibleRoomsForRequest(Long requestId);

    List<RoomDTO> getAll();

    void insertRoom(RoomForm form);
}
