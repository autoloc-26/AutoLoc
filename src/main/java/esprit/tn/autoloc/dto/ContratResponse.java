package esprit.tn.autoloc.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ContratResponse(Long idContrat, LocalDate dateSignature, BigDecimal montantTotal,
                              boolean valide, Long reservationId, List<PaiementResponse> paiements) {}
