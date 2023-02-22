package be.technobel.materialloc.models.form;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class RequestCreateForm {

    private String userLogin;
    private String justification;
    private LocalDate date;
    private LocalTime beginAt;
    private LocalTime endAt;
    private List<Long> materialIds;
    private String additionnalNotes;

}
