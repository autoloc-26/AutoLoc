package esprit.tn.autoloc.web.controller;

import esprit.tn.autoloc.dto.ClientRequest;
import esprit.tn.autoloc.dto.ClientResponse;
import jakarta.validation.Valid;
import esprit.tn.autoloc.services.IClientService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Lister les clients", description = "Recupere la liste complete de les clients")
    @ApiResponse(responseCode = "200", description = "Liste recuperee avec succes")
    @GetMapping
    public List<ClientResponse> findAll() {
        return clientService.findAll();
    }

    @Operation(summary = "Consulter un client", description = "Recupere un client par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouve"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @GetMapping("/{id}")
    public ClientResponse findById(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        return clientService.findById(id);
    }

    @Operation(summary = "Creer un client", description = "Cree un client (sans identifiant dans le corps)")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Client cree avec succes"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(@Valid @RequestBody ClientRequest client) {
        return clientService.create(client);
    }

    @Operation(summary = "Modifier un client", description = "Met a jour un client existant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client modifie avec succes"),
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "400", description = "Donnees invalides")
    })
    @PutMapping("/{id}")
    public ClientResponse update(@Parameter(description = "Identifiant du client") @PathVariable Long id,
                      @Valid @RequestBody ClientRequest client) {
        return clientService.update(id, client);
    }

    @Operation(summary = "Supprimer un client", description = "Supprime un client par son identifiant")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprime avec succes"),
            @ApiResponse(responseCode = "404", description = "Client introuvable")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Parameter(description = "Identifiant du client") @PathVariable Long id) {
        clientService.deleteById(id);
    }
}
