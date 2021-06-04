package pl.polsl.s15.library.commons.annotations;

import pl.polsl.s15.library.commons.validation.AuthRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AuthRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AuthRequest {

    // TODO: add localized messages
    String message() default "Authentication request must contain username and/or email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
