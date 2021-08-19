package com.zup.edu.mercadolivre.compra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, String> {
    boolean existsByIdAndStatus(String id, EnumStatusCompra enumStatusCompra);
}
