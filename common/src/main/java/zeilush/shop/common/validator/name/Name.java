package zeilush.shop.common.validator.name;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Created by AAA on 21.03.2016.
 */
@Constraint(validatedBy = {})
@Pattern(regexp = "[a-zA-Z]", message = "{name.pattern}")
@Size(min = 2, max = 50, message = "{name.size}")
@Documented
@Target({ANNOTATION_TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface Name {
    String message() default "Invalid Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
