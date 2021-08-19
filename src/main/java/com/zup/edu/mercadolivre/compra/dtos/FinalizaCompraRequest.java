package com.zup.edu.mercadolivre.compra.dtos;

import com.zup.edu.mercadolivre.compra.Compra;
import com.zup.edu.mercadolivre.compra.CompraRepository;
import com.zup.edu.mercadolivre.compra.EnumGatewayPagamento;
import com.zup.edu.mercadolivre.compra.EnumStatusCompra;
import com.zup.edu.mercadolivre.exceptions.ExceptionNotFound;
import com.zup.edu.mercadolivre.exceptions.ExisteValid;
import com.zup.edu.mercadolivre.pagamento.Transacao;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public class FinalizaCompraRequest {

    @NotNull
    @ExisteValid(entidade = Compra.class, atributo = "id")
    private final String idCompra;

    @NotNull
    private final String statusCompra;

    @NotNull
    private final EnumGatewayPagamento gateway;

    public FinalizaCompraRequest(String idCompra, String statusCompra, EnumGatewayPagamento pagamento) {
        this.idCompra = idCompra;
        this.statusCompra = statusCompra;
        this.gateway = pagamento;
    }

    public Transacao toModel(CompraRepository compraRepository) {
        boolean existeCompraFinalizada = compraRepository.existsByIdAndStatus(this.idCompra, EnumStatusCompra.FINALIZADA);

        if (existeCompraFinalizada) {
            throw new ExceptionNotFound("Compra já finalizada.", HttpStatus.BAD_REQUEST);
        }

        Compra compra = compraRepository.findById(this.idCompra).get();
        return new Transacao(compra, this.statusCompra, this.gateway);
    }
}
