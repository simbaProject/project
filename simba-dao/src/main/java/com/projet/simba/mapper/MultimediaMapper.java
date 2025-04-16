package com.projet.simba.mapper;

import com.projet.simba.dto.MultimediaDto;
import com.projet.simba.model.Multimedia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MultimediaMapper {

    MultimediaDto toDto(Multimedia multimedia);

    Multimedia toEntity(MultimediaDto multimediaDto);
}
