package com.zup.edu.mercadolivre.pagamento.formaspagamento;

public interface Financeiro<T> {

    String pagar(String idCompra);

    T processarTransacao(String idCompra);

}
