package ru.practicum.ewm.stat_svc.other.validations;

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
        Matcher matcherIpV4 = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$").matcher(ip);
        Matcher matcherIpV6 = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$").matcher(ip);
        return matcherIpV4.matches() || matcherIpV6.matches();
    }
}
