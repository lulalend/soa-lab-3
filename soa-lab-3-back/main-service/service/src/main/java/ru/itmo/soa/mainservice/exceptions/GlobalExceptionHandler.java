package ru.itmo.soa.mainservice.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolationException;
//import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("message", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<Map<String, Object>> handleJsonMappingException(JsonMappingException ex) {
        Map<String, Object> response = new HashMap<>();
        String message = "Invalid input data.";

        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", message);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Invalid date format. Please use 'yyyy-MM-dd' format for date fields.");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("code", HttpStatus.BAD_REQUEST.value());
//
//        String message = ex.getMessage();
//
//        if (message.contains("numeric field overflow")) {
//            String overflowField = extractFieldNameFromMessage(message);
//            String overflowValue = extractOverflowValueFromMessage(message);
//            response.put("message", "Value '" + overflowValue + "' for field '" + overflowField + "' is too large and exceeds the allowed precision. Please provide a smaller number.");
//        } else if (message.contains("duplicate key value")) {
//            String fieldName = extractFieldNameFromMessage(message);
//            response.put("message", "Error: Duplicate value for field '" + fieldName + "'. Please use a unique value.");
//        } else {
//            response.put("message", "A database error occurred. Please check your input.");
//        }
//
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Invalid value for parameter '" + ex.getName() + "': '" + ex.getValue() + "' is not a valid " + ex.getRequiredType().getSimpleName() + ".");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("message", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.NOT_FOUND.value());
        response.put("message", ex.getMessage() != null ? ex.getMessage() : "Resource not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleInvalidParameterException(InvalidParameterException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(BadSqlGrammarException.class)
//    public ResponseEntity<Map<String, Object>> handleBadSqlGrammarException(BadSqlGrammarException e) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("code", HttpStatus.BAD_REQUEST.value());
//        response.put("message", "SQL syntax error: " + e.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Database error occurred: " + e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", HttpStatus.BAD_REQUEST.value());
        response.put("message", "An error occurred: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String extractOverflowValueFromMessage(String message) {
        String[] parts = message.split(" ");
        for (String part : parts) {
            if (part.matches("[-+]?\\d*\\.\\d+|[-+]?\\d+")) {
                return part;
            }
        }
        return "unknown_value";
    }

    private String extractFieldNameFromMessage(String message) {
        if (message.contains("duplicate key value")) {
            String[] parts = message.split(" ");
            for (String part : parts) {
                if (part.contains("_key")) {
                    String[] fieldParts = part.split("_");
                    if (fieldParts.length > 1) {
                        return fieldParts[1];
                    }
                }
            }
        }

        if (message.contains("numeric field overflow")) {
            return "unknown_field";
        }

        return "unknown_field";
    }
}
