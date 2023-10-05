package com.arpan.productcatalog.config;

import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)//only for REST controller
public class GlobalRestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    //@ResponseStatus(value = HttpStatus.CONFLICT, reason="Data integrity violation")//409
    @ExceptionHandler({OptimisticLockingFailureException.class, DataIntegrityViolationException.class })
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getCause().getLocalizedMessage());
        return errors;
//        if (ex.getCause() instanceof ConstraintViolationException) {
//            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
//        }
//        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

//    @ExceptionHandler({SQLException.class, DataAccessException.class})
//    public String databaseError() {
//        // Nothing to do.  Returns the logical view name of an error page, passed
//        // to the view-resolver(s) in usual way.
//        // Note that the exception is NOT available to this view (it is not added
//        // to the model) but see "Extending ExceptionHandlerExceptionResolver"
//        // below.
//        return "databaseError";
//    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ValidationErrors> handleConstraintViolation(ConstraintViolationException ex) {
        //log.debug("Constraint violation exception encountered: {}", ex.getConstraintViolations(), ex);
        //return buildValidationErrors(ex.getConstraintViolations());
        ex.getConstraintViolations().forEach(cex -> {
            System.out.println(cex.getMessage());
        });
        return null;
    }

/*    private List<ValidationErrors>  buildValidationErrors(
            Set<ConstraintViolation<?>> violations) {
        return violations.
                stream().
                map(violation ->
                        ValidationError.builder().
                                field(
                                        StreamSupport.stream(
                                                violation.getPropertyPath().spliterator(), false).
                                                reduce((first, second) -> second).
                                                orElse(null).
                                                toString()
                                ).
                                error(violation.getMessage()).
                                build()).
                collect(toList());
    }*/
}
