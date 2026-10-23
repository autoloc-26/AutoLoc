package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.Entities.Contrat;
import esprit.tn.autoloc.services.IContratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des contrats de location.
 */
@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats de location")
public class ContratController {

    private final IContratService contratService;

    /**
     * Liste tous les contrats.
     * @return Liste de tous les contrats
     */
    @GetMapping
    @Operation(summary = "Lister tous les contrats", description = "Récupère la liste complète des contrats de location")
    @ApiResponse(responseCode = "200", description = "Liste des contrats récupérée avec succès")
    public List<Contrat> findAll() {
        return contratService.findAll();
    }

    /**
     * Consulte un contrat par son identifiant.
     * @param id Identifiant du contrat
     * @return Le contrat trouvé
     */
    @GetMapping("/{id}")
    @Operation(summary = "Consulter un contrat", description = "Récupère un contrat par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat trouvé"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    public Contrat findById(@Parameter(description = "Identifiant du contrat") @PathVariable Long id) {
        return contratService.findById(id);
    }

    /**
     * Crée un nouveau contrat.
     * @param contrat Le contrat à créer
     * @return Le contrat créé avec son identifiant généré
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un contrat", description = "Crée un nouveau contrat de location")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contrat créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Contrat create(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    /**
     * Modifie un contrat existant.
     * @param id Identifiant du contrat à modifier
     * @param contrat Les nouvelles données du contrat
     * @return Le contrat modifié
     */
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un contrat", description = "Met à jour un contrat existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contrat modifié avec succès"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable"),
            @ApiResponse(responseCode = "400", description = "Données invalides")
    })
    public Contrat update(
            @Parameter(description = "Identifiant du contrat") @PathVariable Long id,
            @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    /**
     * Supprime un contrat.
     * @param id Identifiant du contrat à supprimer
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un contrat", description = "Supprime un contrat et ses paiements associés")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contrat supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Contrat introuvable")
    })
    public void deleteById(@Parameter(description = "Identifiant du contrat") @PathVariable Long id) {
        contratService.deleteById(id);
    }
}
