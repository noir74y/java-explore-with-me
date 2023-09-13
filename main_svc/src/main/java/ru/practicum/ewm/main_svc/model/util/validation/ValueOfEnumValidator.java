package ru.practicum.ewm.main_svc.model.util.validation;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumConstraint, CharSequence> {
    List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnumConstraint annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
