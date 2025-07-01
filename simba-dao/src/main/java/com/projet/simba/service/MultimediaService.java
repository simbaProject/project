package com.projet.simba.service;

import com.projet.simba.dto.MultimediaDto;
import org.springframework.web.multipart.MultipartFile;

public interface MultimediaService {

    MultimediaDto uploadMultimedia(MultimediaDto multimediaDto, MultipartFile file);


}
