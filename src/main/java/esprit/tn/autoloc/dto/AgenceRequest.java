package esprit.tn.autoloc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AgenceRequest(
        @NotBlank(message = "Le nom de l'agence est obligatoire")
        @Size(max = 100, message = "Le nom ne doit pas dépasser 100 caractères")
        String nom,

        @NotBlank(message = "La ville est obligatoire")
        @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
        String ville,

        @Size(max = 255, message = "L'adresse ne doit pas dépasser 255 caractères")
        String adresse,

        @Pattern(regexp = "^\\+?[0-9 ]{8,15}$", message = "Numéro de téléphone invalide (8 à 15 chiffres)")
        String telephone
) {}
