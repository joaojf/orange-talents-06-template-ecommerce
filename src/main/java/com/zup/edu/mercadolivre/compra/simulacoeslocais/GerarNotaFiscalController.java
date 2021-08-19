package com.zup.edu.mercadolivre.compra.simulacoeslocais;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GerarNotaFiscalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GerarNotaFiscalController.class);

    @PostMapping(value = "/notas-fiscais")
    public void gerarNF(@RequestBody GerarNotaFiscalRequest request) throws InterruptedException {
        LOGGER.info("Gerando NF para " + request.getIdCompra() + " do usuario " + request.getIdComprador());
        Thread.sleep(100);
    }


}
