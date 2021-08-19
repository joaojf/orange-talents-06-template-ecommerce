package com.zup.edu.mercadolivre.exceptions;

import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaoHaNomeRepetidoNaListaValidator implements ConstraintValidator<NaoHaNomeRepetidoNaListaValid, List<CaracteristicaProdutoRequest>> {

    @Override
    public boolean isValid(List<CaracteristicaProdutoRequest> value, ConstraintValidatorContext context) {
        Set<String> nomesRepetidos = new HashSet<>();
        for (CaracteristicaProdutoRequest c : value) {
            if (!nomesRepetidos.add(c.getNome())) {
                return false;
            }
        } return true;
    }
}
