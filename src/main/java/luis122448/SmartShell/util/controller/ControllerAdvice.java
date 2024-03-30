package luis122448.SmartShell.util.controller;

import luis122448.SmartShell.application.domain.persistence.repository.exception.GenericProcedureException;
import luis122448.SmartShell.security.application.service.exception.GenericAuthServiceException;
import luis122448.SmartShell.util.exception.GenericByteServiceException;
import luis122448.SmartShell.util.exception.GenericListServiceException;
import luis122448.SmartShell.util.exception.GenericObjectServiceException;
import luis122448.SmartShell.util.exception.GenericPageServiceException;
import luis122448.SmartShell.util.object.api.ApiResponseByte;
import luis122448.SmartShell.util.object.api.ApiResponseList;
import luis122448.SmartShell.util.object.api.ApiResponseObject;
import luis122448.SmartShell.util.object.api.ApiResponsePage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Optional;

import static luis122448.SmartShell.util.constant.MESSAGEConstants.ERROR_UNKNOWN;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(Exception e){
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,ERROR_UNKNOWN,e.getMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> ioExceptionHandler(Exception e){
        String logMessage = e.getMessage();
        if (e.getCause() != null){
            logMessage = e.getCause().getMessage();
        }
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,e.getMessage(),logMessage,Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = GenericObjectServiceException.class)
    public ResponseEntity<?> genericObjectServiceException(GenericObjectServiceException e){
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = GenericListServiceException.class)
    public ResponseEntity<?> genericListServiceException(GenericListServiceException e){
        ApiResponseList<?> tmp = new ApiResponseList<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = GenericPageServiceException.class)
    public ResponseEntity<?> genericPageServiceException(GenericPageServiceException e){
        ApiResponsePage<?> tmp = new ApiResponsePage<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = GenericByteServiceException.class)
    public ResponseEntity<?> genericByteServiceException(GenericByteServiceException e){
        ApiResponseByte<?> tmp = new ApiResponseByte<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatus.OK);
    }

    @ExceptionHandler(value = GenericAuthServiceException.class)
    public ResponseEntity<?> genericAuthServiceException(GenericAuthServiceException e){
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = GenericProcedureException.class)
    public ResponseEntity<?> genericProcedureException(GenericProcedureException e){
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(e.getStatus()));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException e){
        String logMessage = e.getMessage();
        if (e.getCause() != null){
            logMessage = e.getCause().getMessage();
        }
        ApiResponseObject<?> tmp = new ApiResponseObject<>(-2,e.getMessage(),logMessage,Optional.empty());
        return new ResponseEntity<>(tmp, HttpStatusCode.valueOf(401));
    }

}
