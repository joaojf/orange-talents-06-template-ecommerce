package com.zup.edu.mercadolivre.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositoy extends JpaRepository<Produto, Long> {
}
