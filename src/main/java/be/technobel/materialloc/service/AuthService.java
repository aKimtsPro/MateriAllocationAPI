package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.form.LoginForm;

public interface AuthService {

    /**
     Logs in the user with the provided login form.
     @param form the login form containing the user's credentials (username and password)
     @return an AuthDTO object containing the user's username, role, and a JSON Web Token (JWT) to be used for subsequent requests
     @throws org.springframework.security.core.AuthenticationException if the authentication fails
     */
    AuthDTO login(LoginForm form);


    AuthDTO refreshJWT(String refreshToken);

}
