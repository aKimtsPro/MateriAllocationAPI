package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.form.RequestForm;
import be.technobel.materialloc.service.MaterialService;
import be.technobel.materialloc.service.RequestService;
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

    public RequestController(MaterialService materialService, RequestService requestService) {
        this.materialService = materialService;
        this.requestService = requestService;
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
        return "request/display-details";
    }

}
