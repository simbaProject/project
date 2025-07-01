package com.projet.simba.controller;

import com.projet.simba.dto.ClientDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.ClientService;
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
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/add")
    @Operation(summary = "add an client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client ajouté avec succès"),
            @ApiResponse(responseCode = "400", description = "données mal entrées")
    })
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto){
        ClientDto client= clientService.createClient(clientDto);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary = "get all clients")
    @ApiResponse(responseCode = "200" ,description = "the found list of clients")
    public ResponseEntity<List<ClientDto>> getAllClient(){
        return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
    }

    @GetMapping("/by-name")
    @Operation(summary = "get all clients with this name")
    @ApiResponse(responseCode = "200" ,description = "the found list of clients")
    public ResponseEntity<List<ClientDto>> getAllClient(@RequestParam String nom){
        return new ResponseEntity<>(clientService.getClient(nom),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get an client by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the client information as object"),
            @ApiResponse(responseCode = "404" ,description = "the ")
    })
    public ResponseEntity<ClientDto> getClient(@Parameter(description = "id of client") @PathVariable UUID id){
        try {
            ClientDto client= clientService.getClient(id);
            return new ResponseEntity<>(client,HttpStatus.OK);
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
    @Operation(summary = "mise a jour des infos d'un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "la mise a jour est un succès"),
            @ApiResponse(responseCode = "404",description = "le client cherché n'existe pas")
    })
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @Parameter(description = "id of client") @PathVariable UUID id){
        try {
            ClientDto client=clientService.updateClient(clientDto,id);
            return new ResponseEntity<>(client,HttpStatus.OK);
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

    @PatchMapping("/{id}/change-date-birth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "mise a jour effectue"),
            @ApiResponse(responseCode = "404",description = "client n'existe pas ")
    })
    public ResponseEntity<ClientDto> updateDateOfBirth(@Valid @RequestBody ClientDto clientDto,@Parameter(description = "id of client") @PathVariable UUID id){
        try {
            ClientDto client=clientService.updateDateOfBirth(id,clientDto);
            return new ResponseEntity<>(client,HttpStatus.OK);
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
    @DeleteMapping("/{id}")
    @Operation(summary = "delete client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the deleting was successfully"),
            @ApiResponse(responseCode = "404",description = "the client is not found")
    }
    )    public ResponseEntity<String> deleteClient(@Parameter(description = "id of client") @PathVariable UUID id){
        if(clientService.deleteClient(id))
            return new ResponseEntity<>("{\"message\" : \"client is deleted successfully\"}",HttpStatus.OK);
        else
            return new ResponseEntity<>("{\"message\" : \"client doesn't exists\"}",HttpStatus.NOT_FOUND);
    }


}
