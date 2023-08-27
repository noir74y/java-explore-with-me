package ru.practicum.ewm.stat_svc.other.validations;

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
@Constraint(validatedBy = HitIpValidator.class)
public @interface HitIpConstraint {
    String message() default "incorrect ip address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
