package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.Entities.Agence;
import esprit.tn.autoloc.services.IAgenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {

    private final IAgenceService agenceService;

    @Operation(summary = "Lister les agences", description = "Recupere la liste complete des agences")
    @ApiResponse(responseCode = "200", description = "Liste recuperee avec succes")
    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @Operation(summary = "Consulter une agence", description = "Recupere une agence par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence trouvee"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @GetMapping("/{id}")
    public Agence findById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        return agenceService.findById(id);
    }

    @Operation(summary = "Creer une agence", description = "Cree une agence (sans identifiant dans le corps)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Agence creee avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @Operation(summary = "Modifier une agence", description = "Met a jour une agence existante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Agence modifiee avec succes"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PutMapping("/{id}")
    public Agence update(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id,
                         @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @Operation(summary = "Supprimer une agence", description = "Supprime une agence par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Agence supprimee avec succes"),
            @ApiResponse(responseCode = "404", description = "Agence introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant de l'agence") @PathVariable Long id) {
        agenceService.deleteById(id);
    }
}