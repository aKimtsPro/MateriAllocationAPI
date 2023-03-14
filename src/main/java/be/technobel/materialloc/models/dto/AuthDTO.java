package be.technobel.materialloc.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthDTO {

    private String token;
    private String refreshToken;
    private String username;
    private List<String> roles;

}
