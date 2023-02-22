package be.technobel.materialloc.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class RequestDTO implements Serializable {

    private final Long id;
    private final LocalDate date;
    private final LocalTime beginTime;
    private final LocalTime endTime;
    private final int neededCapacity;
    private final String justification;
    private final String additionalNotes;
    private final Set<StatusDTO> statusHistory;
    private final PersonDTO madeBy;
    private final RoomDTO room;

}
