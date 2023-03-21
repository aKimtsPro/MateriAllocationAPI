package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.dto.ReducedRequestDTO;
import be.technobel.materialloc.models.dto.RequestDTO;
import be.technobel.materialloc.models.dto.RoomDTO;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.form.RequestForm;
import be.technobel.materialloc.service.RequestService;
import be.technobel.materialloc.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;
    private final RoomService roomService;

    public RequestController(RequestService requestService, RoomService roomService) {
        this.requestService = requestService;
        this.roomService = roomService;
    }

    @PostMapping("/new")
    public void processRequestForm(@RequestBody @Valid RequestForm form){
        requestService.create(form);
    }

    @GetMapping("/future")
    public List<ReducedRequestDTO> displayFutureRequests(@RequestParam(required = false) RequestStatus status){
        return requestService.getFutureWithStatus(status);
    }

    @GetMapping("/{id:[0-9]+}")
    public RequestDTO displayRequestDetails(@PathVariable long id){
        return requestService.getRequestDetails(id);
    }

    @GetMapping("/{id:[0-9]+}/rooms")
    public List<RoomDTO> getCompatibleRooms(@PathVariable long id){
        return roomService.findCompatibleRoomsForRequest(id);
    }

    @PostMapping("/{id:[0-9]+}/refuse")
    public void processRefusalForm( @PathVariable long id, @RequestParam String justification ){
        requestService.refuseRequest(id, justification);
    }

    @PostMapping("/{id:[0-9]+}/relocate")
    public void processRelocateForm( @PathVariable long id, @RequestParam String justification ){
        requestService.relocateRequest(id, justification);
    }

    @PostMapping("/{id:[0-9]+}/accept")
    public void processRelocateForm( @PathVariable long id, @RequestParam String justification, @RequestParam long roomId ){
        requestService.acceptRequest(id, justification, roomId);
    }
}
