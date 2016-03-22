package zeilush.shop.common.validator.name;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by AAA on 21.03.2016.
 */
public class NameValidator implements ConstraintValidator<Name, String> {
    public void initialize(Name constraintAnnotation) {
        // do nothing
    }

    public boolean isValid(String object, ConstraintValidatorContext validationContext) {
        return null != object;
    }
}
