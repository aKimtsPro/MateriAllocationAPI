package be.technobel.materialloc.exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Getter
public class InvalidRoomException extends ResponseStatusException {

    private final List<String> causes;

    public InvalidRoomException(List<String> causes) {
        super(HttpStatus.BAD_REQUEST, causes.toString());
        this.causes = causes;
    }

}
