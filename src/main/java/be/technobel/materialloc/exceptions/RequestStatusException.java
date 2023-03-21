package be.technobel.materialloc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestStatusException extends ResponseStatusException {

    public RequestStatusException() {
        super(HttpStatus.BAD_REQUEST,"invalid request status");
    }
}
