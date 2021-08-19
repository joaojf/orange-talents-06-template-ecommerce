package com.zup.edu.mercadolivre.compra.simulacoeslocais;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtualizarRankingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GerarNotaFiscalController.class);

    @PostMapping(value = "/ranking")
    public void gerarPontuacao(@RequestBody GerarRankingRequest request) throws InterruptedException {
        LOGGER.info("Adicionando pontuação da compra " + request.getIdCompra() + " para o vendedor " + request.getIdVendedor());
        Thread.sleep(100);
    }

}
