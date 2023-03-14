package be.technobel.materialloc.exceptions;

import be.technobel.materialloc.models.entity.BaseEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class NotFoundException extends ResponseStatusException {

    private final Class<? extends BaseEntity<?>> ressourceType;
    private final Object id;

    public NotFoundException(Class<? extends BaseEntity<?>> ressourceType, Object id) {
        this(
                String.format("%s not found with id %s", ressourceType.getSimpleName(), id.toString()),
                ressourceType,
                id
        );
    }

    protected NotFoundException(String message, Class<? extends BaseEntity<?>> ressourceType, Object id){
        super(HttpStatus.NOT_FOUND, message);
        this.ressourceType = ressourceType;
        this.id = id;
    }
}
