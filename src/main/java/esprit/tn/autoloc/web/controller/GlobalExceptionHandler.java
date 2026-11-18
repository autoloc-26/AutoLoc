package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.exception.ErrorResponse;
import esprit.tn.autoloc.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestionnaire centralisé des erreurs pour tous les contrôleurs REST.
 * Chaque exception est traduite en un ErrorResponse avec le code HTTP adapté.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /** 404 : ressource métier introuvable. */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), null, req);
    }

    /** 400 : échec de la validation @Valid sur un DTO (corps de requête). */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> details = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> details.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return build(HttpStatus.BAD_REQUEST, "Erreur de validation des données", details, req);
    }

    /** 400 : contraintes violées en dehors du corps (paramètres, validation manuelle). */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        Map<String, String> details = new LinkedHashMap<>();
        ex.getConstraintViolations()
                .forEach(v -> details.putIfAbsent(v.getPropertyPath().toString(), v.getMessage()));
        return build(HttpStatus.BAD_REQUEST, "Erreur de validation des données", details, req);
    }

    /** 400 : JSON malformé, date illisible ou valeur d'enum inconnue. */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST,
                "Corps de requête invalide : JSON malformé, format de date incorrect ou valeur d'énumération inconnue",
                null, req);
    }

    /** 400 : paramètre d'URL du mauvais type (ex. /clients/abc). */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST,
                "Le paramètre '" + ex.getName() + "' a une valeur invalide : " + ex.getValue(), null, req);
    }

    /** 400 : règle métier violée. */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), null, req);
    }

    /** 405 : verbe HTTP non autorisé (ex. POST /paiements, lecture seule). */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest req) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Méthode HTTP non autorisée : " + ex.getMethod(), null, req);
    }

    /** 404 : route inexistante. */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResource(NoResourceFoundException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "Ressource introuvable : " + req.getRequestURI(), null, req);
    }

    /** 409 : contrainte d'intégrité en base (doublon, clé étrangère). */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest req) {
        return build(HttpStatus.CONFLICT,
                "Opération impossible : violation d'une contrainte d'intégrité (doublon ou élément encore référencé)",
                null, req);
    }

    /** 500 : filet de sécurité, aucun détail technique n'est exposé au client. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, HttpServletRequest req) {
        log.error("Erreur inattendue sur {}", req.getRequestURI(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur interne du serveur", null, req);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String message,
                                                Map<String, String> details, HttpServletRequest req) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                message, req.getRequestURI(), details);
        return ResponseEntity.status(status).body(body);
    }
}
