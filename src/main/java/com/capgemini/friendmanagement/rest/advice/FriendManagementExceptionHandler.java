package com.capgemini.friendmanagement.rest.advice;

import com.capgemini.friendmanagement.response.ErrorResponse;
import com.capgemini.friendmanagement.util.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FriendManagementExceptionHandler {

//    @ExceptionHandler(FriendManagementException.class)
//    public ResponseEntity<ErrorResponse> handleFriendManagementException(FriendManagementException ex, HttpStatus status) {
//        return error(ex.getMessage(), status);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllErrors() {
        return error(ErrorMessages.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> error(String message, HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponse(message), status);
    }
}
