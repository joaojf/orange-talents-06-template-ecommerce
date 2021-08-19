package com.zup.edu.mercadolivre.imagem.uploads;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ImgaemUpload {

    Set<String> enviar(List<MultipartFile> imagens);

}
