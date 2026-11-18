package esprit.tn.autoloc.dto;

import esprit.tn.autoloc.Entities.ModePaiement;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaiementResponse(Long idPaiement, BigDecimal montant, LocalDate datePaiement,
                               ModePaiement modePaiement, Long contratId) {}
