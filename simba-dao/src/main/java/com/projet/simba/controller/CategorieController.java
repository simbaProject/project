package com.projet.simba.controller;

import com.projet.simba.dto.CategorieDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@Tag(name = "api pour les categories de produits")
@AllArgsConstructor
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping("/add")
    @Operation(summary = "ajouter une catregorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ajout de la categorie success"),
            @ApiResponse(responseCode = "400",description = "mauvaise entree de donnees"),
            @ApiResponse(responseCode = "401", description = "action refusee pour cause repetitic=ve")
    })
    public ResponseEntity<CategorieDto> createCategorie(@Valid @RequestBody CategorieDto categorieDto){
        return new ResponseEntity<>(categorieService.createCateg(categorieDto),HttpStatus.CREATED);
    }

    @GetMapping("")
    @Operation(summary = "get the list of categ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the list of")
    })
    public ResponseEntity<List<CategorieDto>> getAllCateg(){
        return new ResponseEntity<>(categorieService.getAllCategorie(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update the categ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the change is completed"),
            @ApiResponse(responseCode = "404",description = "No one of categ")
    })
    public ResponseEntity<CategorieDto> updateCategorie(@Valid @RequestBody CategorieDto categorieDto, UUID id){
        try {
            return new ResponseEntity<>(categorieService.updateCategorie(categorieDto,id),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_ARGUMENT");
            errorModel.setMessage(e.getMessage());
            throw new BusinessException(List.of(errorModel), HttpStatus.NOT_FOUND);
        }
    }
}
