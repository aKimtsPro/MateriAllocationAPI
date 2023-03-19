package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.config.security.JwtProvider;
import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.entity.users.User;
import be.technobel.materialloc.models.form.LoginForm;
import be.technobel.materialloc.repository.UserRepository;
import be.technobel.materialloc.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authManager, JwtProvider jwtProvider, UserRepository userRepository) {
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    @Override
    public AuthDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        form.setPassword(null);

        auth = authManager.authenticate(auth);
        User user = userRepository.findByLogin(form.getUsername())
                .orElseThrow(); // should not happen

        String jwt = jwtProvider.generateToken(user.getUsername(), user.getRole());

        return AuthDTO.builder()
                .username(user.getUsername())
                .roles(List.of(user.getRole()))
                .token( jwt )
                .build();

    }

    @Override
    public AuthDTO refreshJWT(String refreshToken) {
        return null;
    }
}
