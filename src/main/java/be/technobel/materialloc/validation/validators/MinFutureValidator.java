package be.technobel.materialloc.validation.validators;

import be.technobel.materialloc.validation.constraints.MinFuture;
import be.technobel.materialloc.validation.constraints.MinTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;


@Component
public class MinFutureValidator implements ConstraintValidator<MinFuture, Temporal> {

    MinFuture constraint;

    @Override
    public void initialize(MinFuture constraintAnnotation) {
        constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Temporal value, ConstraintValidatorContext context) {

        if( value == null )
            return true;

        boolean valid;

        if( value instanceof LocalDate )
            valid = validateLocalDate( (LocalDate)value );
        else if( value instanceof LocalDateTime )
            valid = validateLocalDateTime( (LocalDateTime)value );
        else if( value instanceof LocalTime )
            valid = validateLocalTime( (LocalTime)value );
        else
            throw new IllegalArgumentException("value should be a LocalDate, LocalDateTime or LocalTime");


        if( !valid )
            setupMessage(context,String.format("should be %d %s in the future", getConstraint().amount(), getConstraint().unit().toString().toLowerCase()));

        return valid;
    }

    private boolean validateLocalDate(LocalDate date){
        return date.isAfter( LocalDate.now().plus(getConstraint().amount(), getConstraint().unit()) );
    }

    private boolean validateLocalDateTime(LocalDateTime dateTime){
        return dateTime.isAfter( LocalDateTime.now().plus(getConstraint().amount(), getConstraint().unit()) );
    }

    private boolean validateLocalTime(LocalTime time){
        return time.isAfter( LocalTime.now().plus(getConstraint().amount(), getConstraint().unit()) );
    }

    protected void setupMessage(ConstraintValidatorContext context, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

    protected MinFuture getConstraint() {
        return constraint;
    }

}
