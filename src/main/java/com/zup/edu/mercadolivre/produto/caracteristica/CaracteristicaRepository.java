package com.zup.edu.mercadolivre.produto.caracteristica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicaRepository extends JpaRepository<CaracteristicaProduto, Long> {
}
