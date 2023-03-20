package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.config.security.JwtProvider;
import be.technobel.materialloc.exceptions.InvalidRefreshTokenException;
import be.technobel.materialloc.models.dto.AuthDTO;
import be.technobel.materialloc.models.entity.RefreshToken;
import be.technobel.materialloc.models.entity.users.User;
import be.technobel.materialloc.models.form.LoginForm;
import be.technobel.materialloc.repository.RefreshTokenRepository;
import be.technobel.materialloc.repository.UserRepository;
import be.technobel.materialloc.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(AuthenticationManager authManager, JwtProvider jwtProvider, RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public AuthDTO login(LoginForm form) {
        // create an Authentication object with the user's credentials
        Authentication auth = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        form.setPassword(null); // clear password for security reasons

        // authenticate the user's credentials with the AuthenticationManager
        auth = authManager.authenticate(auth); // if invalid credentials, AuthenticationException is thrown.

        // generate a JWT using the authenticated user's data
        String jwt = jwtProvider.generateToken(auth);


        // TODO: virer les refresh token invalides.
        User user = userRepository.findByLogin(form.getUsername()).orElseThrow();
        String refreshToken = refreshTokenRepository.findByUser_Login(user.getLogin())
                .map(RefreshToken::getRefreshToken)
                .orElseGet(() -> generateNewRefreshToken(user));

        // build and return an AuthDTO object containing the authenticated user's username, role, and JWT
        return AuthDTO.from(auth, jwt, refreshToken);
    }


    @Override
    public AuthDTO refreshJWT(String refreshToken) {
        RefreshToken rToken = refreshTokenRepository.findAll().stream()
                .filter((token) -> encoder.matches(refreshToken, token.getRefreshToken()))
                .findFirst()
                .orElseThrow(() -> new InvalidRefreshTokenException(refreshToken));

        if( rToken.getExpiresAt().isBefore(LocalDateTime.now()) ) {
            refreshTokenRepository.delete(rToken);
            throw new InvalidRefreshTokenException(refreshToken);
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(rToken.getUser().getUsername(), null, rToken.getUser().getAuthorities());
        return AuthDTO.from(auth, jwtProvider.generateToken(auth), rToken.getRefreshToken());
    }

    private String generateNewRefreshToken(User user){

        String refreshTokenString = UUID.randomUUID().toString();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiresAt(LocalDateTime.now().plusDays(30));
        refreshToken.setRefreshToken( encoder.encode(refreshTokenString) );

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getRefreshToken();
    }
}
