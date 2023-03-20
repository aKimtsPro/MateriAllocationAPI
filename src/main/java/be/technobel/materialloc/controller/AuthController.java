package be.technobel.materialloc.controller;

import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.form.LoginForm;
import be.technobel.materialloc.service.AuthService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/refresh")
    public AuthDTO refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken){
        return authService.refreshJWT(refreshToken);
    }

}
