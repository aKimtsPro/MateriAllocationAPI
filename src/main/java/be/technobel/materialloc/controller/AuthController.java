package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.form.LoginForm;
import be.technobel.materialloc.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form){
        return authService.login(form);
    }

}
