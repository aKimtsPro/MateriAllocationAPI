package be.technobel.materialloc.models.form;

import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.entity.Status;
import be.technobel.materialloc.validation.constraints.MaxTime;
import be.technobel.materialloc.validation.constraints.MinFuture;
import be.technobel.materialloc.validation.constraints.MinTime;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
public class RequestForm {

    @NotBlank
    private String userLogin;
    @NotBlank
    private String justification;
    @Min(1) @Max(300)
    @NotNull
    private Integer neededCapacity;
    @MinFuture(amount = 3, unit = ChronoUnit.DAYS)
    private LocalDate date;
    @MinTime(h=8)
    private LocalTime beginAt;
    @MaxTime(h=18)
    private LocalTime endAt;
    private List<Long> materialIds;
    private String additionalNotes;


    /**
     * map RequestForm to Request entity.
     * Doesn't handle materials and  status
     * @return a Request entity
     */
    public Request toEntity(){

        Request request = new Request();

        request.setDate(this.date);
        request.setJustification(this.justification);
        request.setBeginTime(this.beginAt);
        request.setEndTime(this.endAt);
        request.setNeededCapacity(this.neededCapacity);
        request.setAdditionalNotes(this.additionalNotes);

        return request;

    }

}
