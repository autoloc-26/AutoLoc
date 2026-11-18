package esprit.tn.autoloc.dto;

import esprit.tn.autoloc.Entities.CategorieVehicule;
import esprit.tn.autoloc.Entities.StatutVehicule;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VehiculeRequest(
        @NotBlank(message = "L'immatriculation est obligatoire")
        @Pattern(regexp = "^[A-Za-z0-9 -]{4,20}$", message = "Immatriculation invalide (4 à 20 caractères alphanumériques)")
        String immatriculation,

        @NotBlank(message = "La marque est obligatoire")
        @Size(max = 50, message = "La marque ne doit pas dépasser 50 caractères")
        String marque,

        @NotBlank(message = "Le modèle est obligatoire")
        @Size(max = 50, message = "Le modèle ne doit pas dépasser 50 caractères")
        String modele,

        @NotNull(message = "La catégorie est obligatoire (CITADINE, BERLINE, SUV, UTILITAIRE)")
        CategorieVehicule categorie,

        @NotNull(message = "Le tarif journalier est obligatoire")
        @DecimalMin(value = "0.0", message = "Le tarif journalier ne peut pas être négatif")
        @Digits(integer = 8, fraction = 2, message = "Tarif invalide (8 chiffres max, 2 décimales)")
        BigDecimal tarifJournalier,

        StatutVehicule statut,

        @Positive(message = "L'identifiant de l'agence doit être positif")
        Long agenceId
) {}
