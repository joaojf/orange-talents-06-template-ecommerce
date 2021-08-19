package com.zup.edu.mercadolivre.imagem.uploads;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImagemUploadFake implements ImgaemUpload {
    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens
                .stream()
                .map(imagem -> "https://photoshop.imagem.express.com/" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }

}
