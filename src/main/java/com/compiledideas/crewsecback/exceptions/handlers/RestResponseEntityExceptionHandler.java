package com.compiledideas.crewsecback.exceptions.handlers;


import com.compiledideas.crewsecback.exceptions.ResourceNotFoundException;
import com.compiledideas.crewsecback.exceptions.StorageException;
import com.compiledideas.crewsecback.utils.ResponseHandler;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LogManager.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleConflict(IllegalStateException ex) {
        logger.error("Status:  {} - {}", HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseHandler.generateResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object>  handleUsernameNotFoundException(UsernameNotFoundException ex) {
        logger.error( " - Status:  " + HttpStatus.UNAUTHORIZED + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                "This username is not registered in the database"
        );
    }


    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object>  handleExpiredJwtException(ExpiredJwtException ex) {
        logger.error("Status:  {} - {}", HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseHandler.generateResponse(
                ex.getMessage(),
                HttpStatus.FORBIDDEN,
                "token has been expired"
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object>  handleBadCredentialsException(BadCredentialsException ex) {
        logger.error("Status:  {} - {}", HttpStatus.UNAUTHORIZED, ex.getMessage());
        return ResponseHandler.generateResponse(
                "phone number or password incorrect: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                "Password is incorrect"
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object>  handleNotFoundException(ResourceNotFoundException ex) {
        logger.error("Status:  {} - {}", HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseHandler.generateResponse(
                "NOT FOUND: " + ex.getMessage(),
                HttpStatus.NOT_FOUND,
                null
        );
    }



    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object>  handleAccountStatusException(AccountStatusException ex) {
        logger.error(" - Status:  " + HttpStatus.UNAUTHORIZED + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "User account is abnormal: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                null
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ResponseEntity<Object>  handleAccessDeniedException(AccessDeniedException ex) {
        logger.error(" - Status:  " + HttpStatus.FORBIDDEN + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "No permission: " + ex.getMessage(),
                HttpStatus.FORBIDDEN,
                null
        );
    }

    /* @ExceptionHandler(MessagingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object>  handleMessagingException(MessagingException ex) {
        logger.error(" - Status:  " + HttpStatus.BAD_REQUEST + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "Email Exception: " + ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                null
        );
    } */


    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<Object>  handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        logger.error(" - Status:  " + HttpStatus.UNAUTHORIZED + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "Bad credentials: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED,
                null
        );
    }

    @ExceptionHandler(StorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object>  handleInsufficientAuthenticationException(StorageException ex) {
        logger.error("Status:  " + HttpStatus.BAD_REQUEST + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "Bad credentials: " + ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                null
        );
    }

    @ExceptionHandler( Exception.class )
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleAllException(Exception ex) {
        logger.error("Status:  " + HttpStatus.INTERNAL_SERVER_ERROR + " - " + ex.getMessage());
        return ResponseHandler.generateResponse(
                "Server error: " + ex.getMessage() + " type: " + ex.getClass()  ,
                HttpStatus.INTERNAL_SERVER_ERROR,
                null
        );
    }
}
