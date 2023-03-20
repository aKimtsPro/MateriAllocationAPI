package be.technobel.materialloc.exceptions;

import lombok.Getter;

@Getter
public class InvalidRefreshTokenException extends RuntimeException{

    private final String refreshToken;

    public InvalidRefreshTokenException(String refreshToken) {
        super("invalid refresh token");
        this.refreshToken = refreshToken;
    }
}
