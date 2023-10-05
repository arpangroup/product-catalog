package com.arpan.productcatalog.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = Controller.class)
public class UiExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest req) {
//        logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        System.out.println("UI===>handleValidationExceptions");
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
//        return errors;
        //model.addAttribute("message", "hello");
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", errors.toString());
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error_403_validation.html");
        mav.setViewName("error_403.html");
        return mav;
    }

}
