package com.projet.simba.mapper;

import com.projet.simba.dto.VendeurDto;
import com.projet.simba.model.Vendeur;

//import com.projet.simba.repository.VendeurRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

//import java.util.Optional;
import java.util.UUID;


@Mapper(componentModel = "spring", uses = { PointMapper.class, ProduitMapper.class })
public interface VendeurMapper {

    @Mapping(target = "latitude", source = "geography", qualifiedByName = "extractLatitude")
    @Mapping(target = "longitude", source = "geography", qualifiedByName = "extractLongitude")
    @Mapping(target = "listProduits", source = "listProduits", qualifiedByName = "mapProduitsToIds")
    VendeurDto toDto(Vendeur vendeur);

    @Mapping(target = "geography", expression = "java(pointMapper.createPoint(vendeurDto.getLongitude(), vendeurDto.getLatitude()))")
    @Mapping(target = "listProduits",ignore = true)// on ignorera la liste de produits envoye en dto
    @Mapping(source = "id" ,target = "id" ,ignore = true)
    @Mapping(source = "updateAt" ,target = "updateAt",ignore = true)
    @Mapping(source = "createAt" ,target = "createAt",ignore = true)
    Vendeur toEntity(VendeurDto vendeurDto, @Context PointMapper pointMapper);

    @Named("mapsVendeurToId")
    default UUID mapsVendeurToId(Vendeur vendeur){
        if(vendeur == null)
            return null;
        return vendeur.getId();
    }

   /* @Named("mapsIdToVendeur")
    default Vendeur mapsIdToVendeur(UUID id , @Context VendeurRepository vendeurRepository){
        Optional<Vendeur> optionalVendeur=vendeurRepository.findByIdAndDeleteAtIsNull(id);
        return optionalVendeur.orElse(null);
    }*/

}


