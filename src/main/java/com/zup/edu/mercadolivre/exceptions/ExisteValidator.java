package com.zup.edu.mercadolivre.exceptions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteValidator implements ConstraintValidator<ExisteValid, Object> {

    private Class<?> entidade;
    private String atributo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExisteValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.entidade = constraintAnnotation.entidade();
        this.atributo = constraintAnnotation.atributo();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        String jpql = "select 1 from " + entidade.getName()+ " where " + atributo + "=:value";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
