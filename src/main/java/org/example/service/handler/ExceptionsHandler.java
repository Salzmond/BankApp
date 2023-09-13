package org.example.service.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//
//@ControllerAdvice
//public class ExceptionsHandler {
//
//    @ExceptionHandler()
//    public ResponseEntity handleException() {
//        return null;
//    }
//}

// for Exception intercept and return write Http Status  - nicht 500 in allen Requesten, sondern 400/401 usw.