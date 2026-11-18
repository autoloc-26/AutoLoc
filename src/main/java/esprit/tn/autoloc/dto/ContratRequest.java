package esprit.tn.autoloc.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ContratRequest(
        @NotNull(message = "La date de signature est obligatoire")
        LocalDate dateSignature,

        @NotNull(message = "Le montant total est obligatoire")
        @PositiveOrZero(message = "Le montant total ne peut pas être négatif")
        BigDecimal montantTotal,

        boolean valide,

        @Positive(message = "L'identifiant de la réservation doit être positif")
        Long reservationId,

        @Valid
        List<@NotNull(message = "Un paiement ne peut pas être vide") PaiementRequest> paiements
) {}
