package be.technobel.materialloc.validation.constraints;

import be.technobel.materialloc.validation.validators.MaxTimeValidator;
import be.technobel.materialloc.validation.validators.MinTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxTimeValidator.class)
public @interface MaxTime {

    @AliasFor("hours")
    int h() default 0;
    @AliasFor("h")
    int hours() default 0;
    @AliasFor("minutes")
    int m() default 0;
    @AliasFor("m")
    int minutes() default 0;
    @AliasFor("seconds")
    int s() default 0;
    @AliasFor("s")
    int seconds() default 0;
    int millis() default 0;

    String message() default "max time validation failed";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
