package com.zup.edu.mercadolivre.compra.simulacoeslocais;

import com.zup.edu.mercadolivre.compra.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GerarNotaFiscalRequest {

    @NotNull
    @NotBlank
    private String idCompra;

    @NotNull
    @NotBlank
    private Long idUsuario;

    @Deprecated
    private GerarNotaFiscalRequest() {}

    public GerarNotaFiscalRequest(Compra compra) {
        this.idCompra = compra.getId();
        this.idUsuario = compra.getUsuario().getId();
    }

    public String getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idUsuario;
    }
}
