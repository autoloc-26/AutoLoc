package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.Entities.Paiement;
import esprit.tn.autoloc.services.IPaiementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST en lecture seule pour les paiements.
 * Les paiements sont créés en cascade via leur contrat parent.
 */
@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Consultation des paiements (lecture seule)")
public class PaiementController {

    private final IPaiementService paiementService;

    /**
     * Liste tous les paiements.
     * @return Liste de tous les paiements
     */
    @GetMapping
    @Operation(summary = "Lister tous les paiements", description = "Récupère la liste complète des paiements")
    @ApiResponse(responseCode = "200", description = "Liste des paiements récupérée avec succès")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    /**
     * Consulte un paiement par son identifiant.
     * @param id Identifiant du paiement
     * @return Le paiement trouvé
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulter un paiement", description = "Récupère un paiement par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Paiement trouvé"),
            @ApiResponse(responseCode = "404", description = "Paiement introuvable")
    })
    public Paiement findById(@Parameter(description = "Identifiant du paiement") @PathVariable Long id) {
        return paiementService.findById(id);
    }
}
