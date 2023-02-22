package be.technobel.materialloc.validation.validators;

import be.technobel.materialloc.validation.constraints.MaxTime;
import be.technobel.materialloc.validation.constraints.MinTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;

public class MaxTimeValidator implements ConstraintValidator<MaxTime, Temporal> {

    MaxTime constraint;
    private LocalTime maxTime;

    @Override
    public void initialize(MaxTime constraintAnnotation) {
        constraint = constraintAnnotation;
        maxTime = LocalTime.of(
                getConstraint().h(),
                getConstraint().m(),
                getConstraint().s(),
                getConstraint().millis() * 1_000
        );
    }

    @Override
    public boolean isValid(Temporal value, ConstraintValidatorContext context) {

        if( value == null )
            return true;

        LocalTime toValidate = toLocalTime(value);

        boolean valid = validate(toValidate);
        if( !valid )
            setupMessage(context, "should be max " + maxTime);

        return valid;
    }

    private boolean validate(LocalTime time){
        return time.isBefore( maxTime );
    }

    private LocalTime toLocalTime(Temporal value){
        /*
         * ATTENTION: pattern matching : feature en preview de java 17.
         * Peut ne plus exister par la suite (même si ce serait étonnant)
         */
        if( value instanceof LocalTime )
            return  (LocalTime) value;
        else if (value instanceof LocalDateTime)
            return ((LocalDateTime) value).toLocalTime();
        else
            throw new IllegalArgumentException("value should be LocalTime or LocalDateTime");

    }

    protected void setupMessage(ConstraintValidatorContext context, String message){
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

    protected MaxTime getConstraint() {
        return constraint;
    }
}
