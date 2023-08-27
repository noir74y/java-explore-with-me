package ru.practicum.ewm.stat_svc.dto.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HitIpValidator implements ConstraintValidator<HitsRequestDatesConstraint, String> {
    @Override
    public void initialize(HitsRequestDatesConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String ip, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern =
                Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$");
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}
