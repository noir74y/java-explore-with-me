package ru.practicum.ewm.stat_svc.dto.validations;

import ru.practicum.ewm.stat_svc.dto.HitsRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class HitsRequestDatesValidator implements ConstraintValidator<HitsRequestDatesConstraint, HitsRequest>  {
    @Override
    public void initialize(HitsRequestDatesConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(HitsRequest hitsRequest, ConstraintValidatorContext constraintValidatorContext) {
        var start = hitsRequest.getStart();
        var end = hitsRequest.getEnd();
        return !Objects.isNull(start) && !Objects.isNull(end) && start.isBefore(end);
    }
}
