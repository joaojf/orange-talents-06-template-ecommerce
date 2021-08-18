package com.zup.edu.mercadolivre.exceptions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UnicoValidator.class)
public @interface UnicoValid {

    String message() default "Campo existente, entre com outro valor";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entidade();

    String atributo();

}
