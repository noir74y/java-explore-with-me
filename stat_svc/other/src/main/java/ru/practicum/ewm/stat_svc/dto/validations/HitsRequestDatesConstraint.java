package ru.practicum.ewm.stat_svc.dto.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE_USE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HitsRequestDatesValidator.class)
public @interface HitsRequestDatesConstraint {
    String message() default "End of hits request  has to be later than start of hits request and the both is not null ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
