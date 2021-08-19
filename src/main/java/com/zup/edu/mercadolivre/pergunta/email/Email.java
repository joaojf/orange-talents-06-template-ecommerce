package com.zup.edu.mercadolivre.pergunta.email;

public interface Email {

    void enviar(String corpoEmail, String assunto, String origem, String destino);

    void enviarAvisoTransacaoRecusada(String assunto, String corpoEmail, String linkProduto, String destino);

    void enviarAvisoTransacaoEfetuada(String assunto, String codigoProduto, String produto, String qntd, String valor, String tipoPagamento);

}
