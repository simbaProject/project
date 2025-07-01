package com.projet.simba.controller;

import com.projet.simba.dto.NoteDto;
import com.projet.simba.exceptions.BusinessException;
import com.projet.simba.exceptions.ErrorModel;
import com.projet.simba.service.NoteService;
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
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/add")
    @Operation(summary = "ajouter une note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "note ajoutee avec succes"),
            @ApiResponse(responseCode = "400",description = "mauvaise entree des donnees"),
            @ApiResponse(responseCode = "404",description = "vendeur ou utilisateru non trouve")
    })
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto noteDto, @Parameter(description = "id of user who gives a mark") @RequestParam UUID userId,@Parameter(description = "id of seller") UUID vendeurId){
        try {
            NoteDto note=noteService.createNote(noteDto,userId,vendeurId);
            return new ResponseEntity<>(note , HttpStatus.CREATED);
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
    @Operation(summary = "get an note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found not"),
            @ApiResponse(responseCode = "404",description = "note doesn't exists")
    })
    public ResponseEntity<NoteDto> getNote(@Parameter(description = "ID OF Note") @PathVariable UUID id){
        NoteDto note= noteService.getNote(id);
        if(note!=null)
            return new ResponseEntity<>(note,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list-of-an-user")
    @Operation(summary = "get list of notes by user has given")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found list"),
            @ApiResponse(responseCode = "404",description = "user is not found")
    })
    public ResponseEntity<List<NoteDto>> getAllByUser(@Parameter(description = "Id Of User") @RequestParam("userId")UUID userId){
        try {
            List<NoteDto> noteDtoList = noteService.getNotesByUser(userId);
            return new ResponseEntity<>(noteDtoList,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/list-of-an-prestataire")
    @Operation(summary = "get list of notes for prestataire has given")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the found list"),
            @ApiResponse(responseCode = "500",description = "user is not found")
    })
    public ResponseEntity<List<NoteDto>> getAllForPrestataire(@Parameter(description = "Id Of User") @RequestParam("userId")UUID prestataireId){
        try {
            List<NoteDto> noteDtoList = noteService.getNotes(prestataireId);
            return new ResponseEntity<>(noteDtoList,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModelList=new ArrayList<>();
            ErrorModel errorModel=new ErrorModel();
            errorModel.setCode("BAD_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{id}")
    @Operation(summary = "changer l'avis d'une note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "the avis is up to date"),
            @ApiResponse(responseCode = "404",description = "note is not found")
    })
    public ResponseEntity<NoteDto> updateNote(@Parameter(description = "Id of Note") UUID id,@RequestBody NoteDto noteDto) {
        try {
            NoteDto note = noteService.updateAvis(id, noteDto);
            return new ResponseEntity<>(note,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("BAD_ARGUMENTS");
            errorModel.setMessage(e.getMessage());
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList, HttpStatus.NOT_FOUND);
        }


    }

}
