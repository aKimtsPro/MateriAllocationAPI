package be.technobel.materialloc.models.dto;

import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.entity.Status;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Data
public class ReducedRequestDTO implements Serializable {

    private final Long id;
    private final LocalDate date;
    private final LocalTime beginTime;
    private final LocalTime endTime;
    private final int neededCapacity;
    private final RequestStatus currentStatus;
    private final String madeBy;
    private final Integer roomNumber;
    private final List<MaterialDTO> materials;

    public static ReducedRequestDTO toDto(Request entity){

        if(entity == null)
            return null;

        return new ReducedRequestDTO(
                entity.getId(),
                entity.getDate(),
                entity.getBeginTime(),
                entity.getEndTime(),
                entity.getNeededCapacity(),
                entity.getStatusHistory().stream()
                        .max(Comparator.comparing(Status::getCreatedAt))
                        .map(Status::getStatus)
                        .orElse(null),
                entity.getMadeBy().getFirstName() + ' ' + entity.getMadeBy().getLastName().toUpperCase(),
                entity.getRoom() == null ? null : entity.getRoom().getNumber(),
                entity.getMaterials().stream().map(MaterialDTO::from).toList()
        );

    }


}
