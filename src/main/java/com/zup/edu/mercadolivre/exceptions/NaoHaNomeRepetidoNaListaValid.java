package com.zup.edu.mercadolivre.exceptions;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Constraint(validatedBy = NaoHaNomeRepetidoNaListaValidator.class)
public @interface NaoHaNomeRepetidoNaListaValid {

    String message() default "Existe mais de uma característica com o mesmo nome para este produto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
