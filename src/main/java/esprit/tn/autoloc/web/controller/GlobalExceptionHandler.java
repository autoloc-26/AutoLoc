package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestionnaire global des exceptions pour l'API REST.
 * Traduit les exceptions métier en réponses HTTP appropriées.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère les exceptions ResourceNotFoundException.
     * @param ex l'exception levée
     * @return Réponse HTTP 404 avec détails de l'erreur
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * Gère les exceptions IllegalArgumentException.
     * @param ex l'exception levée
     * @return Réponse HTTP 400 avec détails de l'erreur
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
