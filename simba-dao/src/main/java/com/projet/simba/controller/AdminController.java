package com.projet.simba.controller;

import com.projet.simba.dto.AdminDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.AdminService;
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
@RequestMapping("/admins")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add")
    @Operation(summary = "add a admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "success"),
            @ApiResponse(responseCode = "400",description = "A field is missing or authentification failed"),
    })
    public ResponseEntity<AdminDto> createAdmin(@Valid @RequestBody AdminDto adminDto){
        AdminDto admin=adminService.createAdmin(adminDto);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get an admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "the found amin and his information"),
            @ApiResponse(responseCode = "404",description = "Not admin found for that id")
    })
    public ResponseEntity<AdminDto> getAdmin(@Parameter(description = "id of admin") @PathVariable UUID id){
        try {
            AdminDto admin=adminService.getAdmin(id);
            return new ResponseEntity<>(admin,HttpStatus.OK);
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
    @GetMapping("/all")
    @Operation(summary = "Get admins")
    @ApiResponse(responseCode = "200",description = "The list of admin")
    public ResponseEntity<List<AdminDto>> getAdmins(){
        return new ResponseEntity<>(adminService.getAll(),HttpStatus.OK);
    }
    @PatchMapping("/{id}/update-entryKey")
    @Operation(summary = "partial updating of an admim")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the updating of entryKey is okay"),
            @ApiResponse(responseCode = "404",description = "admin doesn't exist"),
            @ApiResponse(responseCode ="400",description = "operation refusée droits insuffisqnts")
    })
    public ResponseEntity<AdminDto> updateEntryKey(@Parameter(description = "Id Of Admin") @PathVariable UUID id,@Valid @RequestBody AdminDto adminDto){
        try {
            AdminDto admin=adminService.updateEntryKey(id,adminDto);
            return new ResponseEntity<>(admin,HttpStatus.OK);
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
    @GetMapping("/{nom}/get-by-name")
    @Operation(summary = "Get Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found admin"),
            @ApiResponse(responseCode = "404",description = "Admin is not found")
    })
    public ResponseEntity<AdminDto> getAdmin(@Parameter(description = "Name of Admin") @PathVariable String nom) {
        try {
            AdminDto admin = adminService.getAdmin(nom);
            return new ResponseEntity<>(admin,HttpStatus.OK);
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
    @Operation(summary = "full updating of an admim")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the updating is okay"),
            @ApiResponse(responseCode = "404",description = "admin doesn't exist")
    })
    public ResponseEntity<AdminDto> updateAdmin(@Parameter(description = "Id of Admin") @PathVariable UUID id,@Valid @RequestBody AdminDto adminDto){
        try {
            AdminDto admin=adminService.updateAdmin(id,adminDto);
            return new ResponseEntity<>(admin,HttpStatus.OK);
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
    @Operation(summary = "delete admin")
    public ResponseEntity<String> deleteAdmin(@Parameter(description = "Id Of Admin") @PathVariable UUID id){
        if(adminService.deleteAdmin(id))
            return new ResponseEntity<>("{\"message\" : \"admin is deleted successfully\"}",HttpStatus.OK);
        else
            return new ResponseEntity<>("{\"message\" : \"admin doesn't exists\"}",HttpStatus.NOT_FOUND);
    }


}
