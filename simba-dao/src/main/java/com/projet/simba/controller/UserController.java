package com.projet.simba.controller;

import com.projet.simba.dto.UserDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.UserService;
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
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}/change-mail")
    @Operation(summary = "change just mail of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "le mail a été changé avec succès"),
            @ApiResponse(responseCode = "404" ,description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<UserDto> updateMail(@Valid @RequestBody UserDto userDto, @Parameter(description = "id of user") @PathVariable UUID id){
        try {
            UserDto user=userService.updateUserMail(id,userDto);
            return new ResponseEntity<>(user, HttpStatus.OK);
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
    @PatchMapping("/{id}/change-coordinates")
    @Operation(summary = "change just position of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "les coordonnées  ont été changés avec succès"),
            @ApiResponse(responseCode = "404" ,description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<UserDto> updateCoordinates(@Valid @RequestBody UserDto userDto,UUID id){
        try {
            UserDto user= userService.updateCoordonnees(id ,userDto);
            return new ResponseEntity<>(user , HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/change-montant")
    @Operation(summary="Update Montant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the updating is ok"),
            @ApiResponse(responseCode = "404",description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<UserDto> updateMontantCompte(@Parameter(description = "Id of user") @PathVariable UUID id,@Valid @RequestBody UserDto userDto){
        try{
            UserDto user=userService.updateMontantCompte(id,userDto);
            return new ResponseEntity<>(user,HttpStatus.OK);
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
    @PatchMapping("/{id}/change-password")
    @Operation(summary = "partial updating")
    @ApiResponses(
            value={
                    @ApiResponse(responseCode = "200",description = "the password is up-to-date"),
                    @ApiResponse(responseCode = "404",description = "the user doen't exists"),
                    @ApiResponse(responseCode = "500",description = "the old password is not correct")
            }
    )
    public ResponseEntity<String> updatePassWord(@Parameter(description = "id of user") @PathVariable UUID id,@Valid @RequestBody UserDto userDto,@Parameter(description = "The old password") @RequestParam String oldPassWord){
        try{
            userService.updateUserPassWord(id,userDto,oldPassWord);
            return new ResponseEntity<>("{" +
                    "\"message\" : \"Mot de passe mis à jour avec succès\"" +
                    "}", HttpStatus.OK);
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
    @PatchMapping("/{id}/change-address")
    @Operation(summary = "change just location of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "l'adresse  a été changée avec succès"),
            @ApiResponse(responseCode = "404" ,description = "l'utilisateur n'existe pas")
    })
    public ResponseEntity<UserDto> updateAddress(@Valid @RequestBody UserDto userDto, @Parameter(description = "id of seller") @PathVariable UUID id){
        try {
            UserDto user= userService.updateLocation(id ,userDto);
            return new ResponseEntity<>(user , HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModels=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_DATA_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModels.add(errorModel);
            throw new BusinessException(errorModels , HttpStatus.NOT_FOUND);
        }
    }


}
