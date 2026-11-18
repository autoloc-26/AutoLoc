package esprit.tn.autoloc.dto;

import esprit.tn.autoloc.Entities.ModePaiement;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementRequest(
        @NotNull(message = "Le montant du paiement est obligatoire")
        @Positive(message = "Le montant du paiement doit être strictement positif")
        BigDecimal montant,

        @NotNull(message = "La date de paiement est obligatoire")
        LocalDate datePaiement,

        @NotNull(message = "Le mode de paiement est obligatoire (CARTE, ESPECES, VIREMENT)")
        ModePaiement modePaiement
) {}
