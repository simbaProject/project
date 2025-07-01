package com.projet.simba.mapper;

import com.projet.simba.dto.AdminDto;
import com.projet.simba.model.Admin;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T18:03:37+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Ubuntu)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Autowired
    private PointMapper pointMapper;

    @Override
    public AdminDto toDto(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        adminDto.setLatitude( pointMapper.extractLatitude( admin.getGeography() ) );
        adminDto.setLongitude( pointMapper.extractLongitude( admin.getGeography() ) );
        adminDto.setId( admin.getId() );
        adminDto.setNom( admin.getNom() );
        adminDto.setMotDePasse( admin.getMotDePasse() );
        adminDto.setLocalisation( admin.getLocalisation() );
        adminDto.setAdresseMail( admin.getAdresseMail() );
        adminDto.setCreateAt( admin.getCreateAt() );
        adminDto.setUpdateAt( admin.getUpdateAt() );
        adminDto.setRole( admin.getRole() );
        adminDto.setMontantCompte( admin.getMontantCompte() );
        adminDto.setEntryKey( admin.getEntryKey() );

        return adminDto;
    }

    @Override
    public Admin toEntity(AdminDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setNom( adminDto.getNom() );
        admin.setMotDePasse( adminDto.getMotDePasse() );
        admin.setRole( adminDto.getRole() );
        admin.setAdresseMail( adminDto.getAdresseMail() );
        admin.setLocalisation( adminDto.getLocalisation() );
        admin.setMontantCompte( adminDto.getMontantCompte() );
        admin.setEntryKey( adminDto.getEntryKey() );

        return admin;
    }
}
