package com.projet.simba.controller;

import com.projet.simba.dto.EntrepriseLivraisonDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.EnterpriseLivraisonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enterprises-liv")
@AllArgsConstructor
public class EntrepriseLivraisonController {

    private final EnterpriseLivraisonService enterpriseLivraisonService;

    @PostMapping("/add")
    @Operation(summary = "ajouter une entreprise de livraison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "entreprise ajoutee avec succes"),
            @ApiResponse(responseCode = "400" , description = "mauvaise entree des donnees"),
            @ApiResponse(responseCode = "404" , description = "if the contract reference is not found")
    })
    public ResponseEntity<EntrepriseLivraisonDto> createEnterprise(@Valid @RequestBody EntrepriseLivraisonDto entrepriseLivraisonDto){
        try {
            EntrepriseLivraisonDto entreprise=enterpriseLivraisonService.createEnterprise(entrepriseLivraisonDto);
            return new ResponseEntity<>(entreprise, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("CONDITION_NOT_SUFFICIENT");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "get an enterprise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found enterprise"),
            @ApiResponse(responseCode = "404",description = "enterprise doen't exists")
    })
    public ResponseEntity<EntrepriseLivraisonDto> getEnterprise(@Parameter(description = "id of enterprise") @PathVariable UUID id){
        try {
            EntrepriseLivraisonDto entrepriseLivraisonDto= enterpriseLivraisonService.getEnterprise(id);
            return new ResponseEntity<>(entrepriseLivraisonDto,HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "get the list of all enterprises of livraison")
    @ApiResponse(responseCode = "200", description = "the found list")
    public ResponseEntity<List<EntrepriseLivraisonDto>> getEnterprises(){
        return new ResponseEntity<>(enterpriseLivraisonService.getEnterprises(),HttpStatus.OK);
    }

    @GetMapping("/{name}/nom")
    @Operation(summary = "get an enterprise by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found enterprise"),
            @ApiResponse(responseCode = "404",description = "enterprise doesn't exists")
    })
    public ResponseEntity<EntrepriseLivraisonDto> getEnterpriseByNom(@Parameter(description = "name of enterprise") @PathVariable String name){
        try {
            EntrepriseLivraisonDto entrepriseLivraisonDto= enterpriseLivraisonService.getEnterprise(name);
            return new ResponseEntity<>(entrepriseLivraisonDto,HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "mise a jour d'une entreprise de livraison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "la mise a jour fut un succès"),
            @ApiResponse(responseCode = "400" , description = "mauvaise entree des donnees"),
            @ApiResponse(responseCode = "404" , description = "entreprise de livraison pas trouvee")
    })
    public ResponseEntity<EntrepriseLivraisonDto> updateEnterprise(@Valid @RequestBody EntrepriseLivraisonDto entrepriseLivraisonDto, @Parameter(description = "id of enterprise") @PathVariable UUID id){
        try {
            EntrepriseLivraisonDto entreprise=enterpriseLivraisonService.updateEnterprise(id,entrepriseLivraisonDto);
            return new ResponseEntity<>(entreprise,HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete enterprise by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "la suppression est un succes"),
            @ApiResponse(responseCode = "404",description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<String> deleteEnterprise(@Parameter(description = "id of enterprise") @PathVariable UUID id){
        boolean deleted = enterpriseLivraisonService.deleteEnterprise(id);
        if(deleted)
            return new ResponseEntity<>("{" +
                    "\"message\" : \"seller is deleted successfully\"" +
                    "}",HttpStatus.OK);
        else
            return new ResponseEntity<>("{" +
                    "\"message\" : \"seller doesn't exists\"" +
                    "}",HttpStatus.NOT_FOUND);
    }



}
