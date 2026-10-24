package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.Entities.Vehicule;
import esprit.tn.autoloc.services.IVehiculeService;
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
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicules", description = "Gestion du parc de vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @Operation(summary = "Lister les vehicules", description = "Recupere la liste complete des vehicules")
    @ApiResponse(responseCode = "200", description = "Liste recuperee avec succes")
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @Operation(summary = "Consulter un vehicule", description = "Recupere un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule trouve"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    @GetMapping("/{id}")
    public Vehicule findById(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @Operation(summary = "Creer un vehicule", description = "Cree un vehicule (sans identifiant dans le corps)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicule cree avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @Operation(summary = "Modifier un vehicule", description = "Met a jour un vehicule existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicule modifie avec succes"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PutMapping("/{id}")
    public Vehicule update(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id,
                           @RequestBody Vehicule vehicule) {
        return vehiculeService.update(id, vehicule);
    }

    @Operation(summary = "Supprimer un vehicule", description = "Supprime un vehicule par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Vehicule supprime avec succes"),
            @ApiResponse(responseCode = "404", description = "Vehicule introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant du vehicule") @PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}