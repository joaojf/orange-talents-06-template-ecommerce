package com.zup.edu.mercadolivre.pergunta.email;

import com.zup.edu.mercadolivre.compra.Compra;
import com.zup.edu.mercadolivre.pagamento.Transacao;
import com.zup.edu.mercadolivre.pergunta.Pergunta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class EnviarEmail {

    private final Email email;

    @Autowired
    public EnviarEmail(Email email) {
        this.email = email;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EnviarEmail.class);

    public void pergunta(Pergunta pergunta) {
        email.enviar("<html>...</html>", pergunta.getTitulo(),
                pergunta.getUsuario().getLogin(),
                pergunta.getProduto().getUsuario().getLogin());
    }

    public void desejoDeCompra(@NotNull @Valid Compra compra) {
        LOGGER.info("desejoDeCompra");
        email.enviar("<html> . . . </html>",
                compra.getProduto().getNome(),
                compra.getUsuario().getLogin(),
                compra.getProduto().getUsuario().getLogin());
    }

    public void avisoTransacaoRecusada(@NotNull @Valid Transacao transacao) {
        LOGGER.info("avisoTransacaoRecusada");
        email.enviarAvisoTransacaoRecusada("Pagamento Recusado",
                "Falha ao processar o pagamento do produto " +
                        transacao.getCompra().getProduto().getNome(),
                "Tente novamente: http://localhost:8080/produtos/detalhe/" +
                        transacao.getCompra().getId(),
                transacao.getCompra().getUsuario().getLogin());
    }

    public void transacaoEfetuada(@NotNull @Valid Transacao transacao) {
        LOGGER.info("avisoTransacaoEfetuada");
        email.enviarAvisoTransacaoEfetuada("Compra efetuada com sucesso!",
                "SKU:" + transacao.getCompra().getProduto().getId(),
                "Produto:" + transacao.getCompra().getProduto().getNome(),
                "Quantidade adquirida:" + transacao.getCompra().getQuantidade(),
                "Valor total:" + transacao.getCompra().getValorTotal(),
                "Forma de pagamento:" + transacao.getGateway().toString());
    }

}
