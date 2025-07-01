package com.projet.simba.controller;

import com.projet.simba.dto.VendeurDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.VendeurService;
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
@RequestMapping("/vendeurs")
@AllArgsConstructor
public class VendeurController {

    private final VendeurService vendeurService;

    @PostMapping("/add")
    @Operation(summary = "create an seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "adding seller ... operation completed"),
            @ApiResponse(responseCode = "400", description = "bad entry of data")
    })
    public ResponseEntity<VendeurDto> createVendeur(@Valid @RequestBody VendeurDto vendeurDto){

            VendeurDto vendeur=vendeurService.createVendeur(vendeurDto);
            return new ResponseEntity<>(vendeur, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "get all sellers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<List<VendeurDto>> getVendeur(){
        List<VendeurDto> vendeurDtos=vendeurService.getVendeurs();
        return new ResponseEntity<>(vendeurDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}/id")
    @Operation(summary = "get an seller by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "check the seller's information"),
            @ApiResponse(responseCode = "404" ,description = "the seller doesn't exists")
    })
    public  ResponseEntity<VendeurDto> getVendeur(@Parameter(description = "id of vendeur") @PathVariable UUID id){

        try {
            VendeurDto vendeur= vendeurService.getUser(id);
            return new ResponseEntity<>(vendeur,HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/{nom}/name")
    @Operation(summary = "get an seller by his name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "check the seller's information"),
            @ApiResponse(responseCode = "404" ,description = "the seller doesn't exists")
    })
    public  ResponseEntity<VendeurDto> getVendeurByName(@Parameter(description = "id of vendeur") @PathVariable String nom){

        try {
            VendeurDto vendeur= vendeurService.getUser(nom);
            return new ResponseEntity<>(vendeur,HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "change profil of seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "the updating was completed"),
            @ApiResponse(responseCode = "404" , description = "the user doesn't exists")
    })
    public ResponseEntity<VendeurDto> updateVendeur(@Valid @RequestBody VendeurDto vendeurDto, @Parameter(description = "id of seller") @PathVariable UUID id){
        try {
            VendeurDto vendeur= vendeurService.updateVendeur(id ,vendeurDto);
            return new ResponseEntity<>(vendeur , HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{id}/change-name")
    @Operation(summary = "change just name of seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "le nom a été changé avec succès"),
            @ApiResponse(responseCode = "404" ,description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<VendeurDto> updateName(@Valid @RequestBody VendeurDto vendeurDto , @Parameter(description = "id of seller") @PathVariable UUID id){
        try {
            VendeurDto vendeur= vendeurService.updateVendeurName(id ,vendeurDto);
            return new ResponseEntity<>(vendeur , HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "delete an seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" ,description = "the deleting was completed"),
            @ApiResponse(responseCode = "404" ,description = "the seller is not found")
    })
    public ResponseEntity<String> deleteVendeur(@Parameter(description = "id of seller") @PathVariable UUID id){
        try {
            boolean deleted = vendeurService.deleteVendeur(id);
            if(deleted)
                return new ResponseEntity<>("{" +
                        "\"message\" : \"seller is deleted successfully\"" +
                        "}",HttpStatus.OK);
            else
                return new ResponseEntity<>("{" +
                        "\"message\" : \"seller doesn't exists\"" +
                        "}",HttpStatus.NOT_FOUND);
        }
        catch(IllegalArgumentException e){
                return  new ResponseEntity<>(e.getMessage() ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all-by-product")
    @Operation(summary = "avoir la liste des vendeurs qui proposent un produit de tel libelle")
    @ApiResponse(responseCode = "200" ,description = "the found list")
    public ResponseEntity<List<VendeurDto>> getVendeursByProduuct(@Parameter(description = "libelle of product") @RequestParam String libelle){
        return new ResponseEntity<>(vendeurService.getVendeursByProduct(libelle),HttpStatus.OK);
    }
}
