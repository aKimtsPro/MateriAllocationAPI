package be.technobel.materialloc.models.dto;

import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.entity.Status;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class StatusDTO implements Serializable {
    private final LocalDateTime createdAt;
    private final Long id;
    private final RequestStatus status;
    private final String justification;

    public static StatusDTO toDto(Status entity){
        if( entity == null )
            return null;

        return new StatusDTO(
                entity.getCreatedAt(),
                entity.getId(),
                entity.getStatus(),
                entity.getJustification()
        );
    }

}
