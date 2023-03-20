package be.technobel.materialloc.models.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@Builder
public class AuthDTO {

    private String token;
    private String refreshToken;
    private String username;
    private List<String> roles;

    public static AuthDTO from(Authentication auth, String token, String refreshToken){
        return AuthDTO.builder()
                .username(auth.getName())
                .roles(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

}
