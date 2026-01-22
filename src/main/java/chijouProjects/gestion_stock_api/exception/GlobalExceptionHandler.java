package chijouProjects.gestion_stock_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gestion des InvalidEntityException (ex: BAD_CREDENTIALS)
    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEntityException(InvalidEntityException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("httpCode", HttpStatus.BAD_REQUEST.value()); // 400
        body.put("message", ex.getMessage());
        // ⚡ Met le code numérique de ton enum
        body.put("code", ex.getErrorCode() != null ? ex.getErrorCode().getCode() : null);
        body.put("errors", ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // Gestion des EntityNotFoundException (ex: UTILISATEUR_NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("httpCode", HttpStatus.NOT_FOUND.value()); // 404
        body.put("message", ex.getMessage());
        body.put("code", ex.getErrorCode() != null ? ex.getErrorCode().getCode() : null);
        body.put("errors", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // Gestion générique pour les autres exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("httpCode", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
        body.put("message", ex.getMessage());
        body.put("code", null);
        body.put("errors", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
