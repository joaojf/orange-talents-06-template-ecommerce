package com.zup.edu.mercadolivre.pergunta.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmailFake implements Email {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnviarEmail.class);

    @Override
    public void enviar(String corpoEmail, String assunto, String origem, String destino) {
        LOGGER.info(corpoEmail);
        LOGGER.info(assunto);
        LOGGER.info(origem);
        LOGGER.info(destino);
    }

    @Override
    public void enviarAvisoTransacaoRecusada(String assunto, String corpoEmail, String linkProduto, String destino) {
        LOGGER.info(assunto);
        LOGGER.info(corpoEmail);
        LOGGER.info(linkProduto);
        LOGGER.info(destino);
    }

    @Override
    public void enviarAvisoTransacaoEfetuada(String assunto, String codigoProduto, String produto, String qntd, String valor, String tipoPagamento) {
        LOGGER.info(assunto);
        LOGGER.info(codigoProduto);
        LOGGER.info(produto);
        LOGGER.info(qntd);
        LOGGER.info(valor);
        LOGGER.info(tipoPagamento);
    }
}
