package com.zup.edu.mercadolivre.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValidator implements ConstraintValidator<UnicoValid, Object> {

    @Autowired
    private EntityManager entityManager;

    private Class<?> entidade;
    private String atributo;

    @Override
    public void initialize(UnicoValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entidade = constraintAnnotation.entidade();
        this.atributo = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String jpql = "select 1 from " + entidade.getName() + " where " + atributo + " = :pValor";

        Query query = entityManager.createQuery(jpql);
        query.setParameter("pValor", value);
//        boolean empty = query.getResultList().isEmpty();
        return query.getResultList().isEmpty();
    }

}
