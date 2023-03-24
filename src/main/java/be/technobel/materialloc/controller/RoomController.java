package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.dto.RoomDTO;
import be.technobel.materialloc.models.form.RoomForm;
import be.technobel.materialloc.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDTO> getAll(){
        return roomService.getAll();
    }

    @PostMapping
    public void insert(@RequestBody @Valid RoomForm form){
        roomService.insertRoom(form);
    }
}
