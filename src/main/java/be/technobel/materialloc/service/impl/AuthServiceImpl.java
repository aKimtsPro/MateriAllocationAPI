package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.form.LoginForm;
import be.technobel.materialloc.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthDTO login(LoginForm form) {
        return null;
    }
}
