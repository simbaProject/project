package com.projet.simba.controller;

import com.projet.simba.dto.ContratDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.ContratService;
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

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContratController {
    private final ContratService contratService;

    @PostMapping("/add")
    @Operation(summary = "add a new contract for an enterprise of livraison")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "adding of contract completed"),
            @ApiResponse(responseCode = "400" , description = "bad entry of data")
    })
    public ResponseEntity<ContratDto> createContract(@Valid @RequestBody ContratDto contratDto){
        ContratDto contrat=contratService.createContract(contratDto);
        return new ResponseEntity<>(contrat, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "get all contracts of all enterprises")
    @ApiResponse(responseCode = "200",description = "the found list of contracts")
    public ResponseEntity<List<ContratDto>> getAll(){
        return new ResponseEntity<>(contratService.getContracts(),HttpStatus.OK);
    }

    @GetMapping("/{ref}")
    @Operation(summary = "get an contract by reference")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "the found contract"),
            @ApiResponse(responseCode = "404" ,description = "no contract found with this reference")
    }
    )
    public ResponseEntity<ContratDto> getReference(@Parameter(description = "reference of contract") @PathVariable String ref){
        try {
            ContratDto contrat= contratService.getContract(ref);
            return new ResponseEntity<>(contrat,HttpStatus.OK);
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

    @PutMapping("/{ref}")
    @Operation(summary = "update an contract")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "the found contract"),
            @ApiResponse(responseCode = "404" ,description = "no contract found with this reference")
    }
    )
    public ResponseEntity<ContratDto> updateContract(@Valid @RequestBody ContratDto contratDto, @Parameter(description = "reference of contract") @PathVariable String ref){
        try {
            ContratDto contract=contratService.updateContract(ref,contratDto);
            return new ResponseEntity<>(contract,HttpStatus.OK);
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

    @DeleteMapping("/{ref}")
    @Operation(summary = "delete an contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleting of contract... operation completed "),
            @ApiResponse(responseCode = "404" ,description = "no contract found with this reference")
    })
    public ResponseEntity<String> deleteContract(@Parameter(description = "reference of contract") @PathVariable String ref){
        if(contratService.deleteContract(ref))
            return new ResponseEntity<>("{\"message\" : \"contract is deleted successfully\"}",HttpStatus.OK);
        else
            return new ResponseEntity<>("{\"message\" : \"contract doesn't exists\"}",HttpStatus.NOT_FOUND);
    }


}
