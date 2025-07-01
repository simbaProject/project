package com.projet.simba.mapper;

import com.projet.simba.dto.MultimediaDto;
import com.projet.simba.model.Multimedia;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class MultimediaMapperImpl implements MultimediaMapper {

    @Override
    public MultimediaDto toDto(Multimedia multimedia) {
        if ( multimedia == null ) {
            return null;
        }

        MultimediaDto multimediaDto = new MultimediaDto();

        multimediaDto.setMultimedia( toDto( multimedia.getMultimedia() ) );
        multimediaDto.setType( multimedia.getType() );
        multimediaDto.setCheminVersImage( multimedia.getCheminVersImage() );
        multimediaDto.setCreateAt( multimedia.getCreateAt() );
        multimediaDto.setUpdateAt( multimedia.getUpdateAt() );
        multimediaDto.setDeleteAt( multimedia.getDeleteAt() );

        return multimediaDto;
    }

    @Override
    public Multimedia toEntity(MultimediaDto multimediaDto) {
        if ( multimediaDto == null ) {
            return null;
        }

        Multimedia multimedia = new Multimedia();

        multimedia.setMultimedia( toEntity( multimediaDto.getMultimedia() ) );
        multimedia.setType( multimediaDto.getType() );
        multimedia.setCheminVersImage( multimediaDto.getCheminVersImage() );
        multimedia.setDeleteAt( multimediaDto.getDeleteAt() );

        return multimedia;
    }
}
