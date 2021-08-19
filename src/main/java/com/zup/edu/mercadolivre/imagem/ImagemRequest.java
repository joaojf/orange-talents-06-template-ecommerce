package com.zup.edu.mercadolivre.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ImagemRequest {

    @Size(min = 1)
    @NotNull
    private final List<MultipartFile> listaImagens;

    public ImagemRequest(List<MultipartFile> listaImagens) {
        this.listaImagens = listaImagens;
    }

    public List<MultipartFile> getListaImagens() {
        return listaImagens;
    }
}
