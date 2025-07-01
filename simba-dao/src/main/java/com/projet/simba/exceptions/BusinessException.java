package com.projet.simba.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException{
    List<ErrorModel> errorModels;
    HttpStatus status;
    public BusinessException(List<ErrorModel> errorModels , HttpStatus status){
        this.errorModels=errorModels;
        this.status=status;
    }
}
