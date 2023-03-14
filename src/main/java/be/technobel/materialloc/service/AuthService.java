package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.form.LoginForm;

public interface AuthService {

    AuthDTO login(LoginForm form);

}
