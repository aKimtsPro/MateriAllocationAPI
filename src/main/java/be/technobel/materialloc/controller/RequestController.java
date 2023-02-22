package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.form.RequestForm;
import be.technobel.materialloc.service.MaterialService;
import be.technobel.materialloc.service.RequestService;
import be.technobel.materialloc.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request")
public class RequestController {

    private final MaterialService materialService;
    private final RequestService requestService;
    private final RoomService roomService;

    public RequestController(MaterialService materialService, RequestService requestService, RoomService roomService) {
        this.materialService = materialService;
        this.requestService = requestService;
        this.roomService = roomService;
    }

    @GetMapping("/new")
    public String displayRequestForm(@ModelAttribute("form") RequestForm form, Model model){
        model.addAttribute("materials", materialService.getAll());
        return "request/request-create-form";
    }

    @PostMapping("/new")
    public String processRequestForm(@ModelAttribute("form") @Valid RequestForm form, BindingResult bindingResult){
        if( bindingResult.hasErrors() )
            return "request/request-create-form";

        requestService.create(form);
        return "redirect:/";
    }

    @GetMapping({
            "/{status:PENDING}",
            "/{status:REFUSED}",
            "/{status:ACCEPTED}",
            "/{status:RELOCATING}"
    })
    public String displayFutureRequests(@PathVariable RequestStatus status, Model model){
        model.addAttribute("active_status", status.name());
        model.addAttribute("requests", requestService.getFutureWithStatus(status));
        return "request/display-future";
    }

    @GetMapping("/{id:[0-9]+}")
    public String displayRequestDetails(@PathVariable long id, Model model){
        model.addAttribute("request", requestService.getRequestDetails(id));
        return "request/display-details";
    }

    @GetMapping({
            "/{id:[0-9]+}/{action:refuse}",
            "/{id:[0-9]+}/{action:relocate}"
    })
    public String displayChangeStatusForm(@ModelAttribute @PathVariable long id, @ModelAttribute @PathVariable String action){
        return "request/change-status-form";
    }

    @GetMapping("/{id:[0-9]+}/accept")
    public String displayAcceptForm(@ModelAttribute @PathVariable long id, Model model){
        model.addAttribute("rooms", roomService.findCompatibleRoomsForRequest(id));
        return "request/accept-form";
    }

    @PostMapping("/{id:[0-9]+}/refuse")
    public String processRefusalForm( @PathVariable long id, @RequestParam String justification ){
        requestService.refuseRequest(id, justification);
        return "redirect:/request/"+id;
    }

    @PostMapping("/{id:[0-9]+}/relocate")
    public String processRelocateForm( @PathVariable long id, @RequestParam String justification ){
        requestService.relocateRequest(id, justification);
        return "redirect:/request/"+id;
    }


}