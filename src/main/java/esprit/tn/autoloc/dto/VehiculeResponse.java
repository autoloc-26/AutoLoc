package esprit.tn.autoloc.dto;

import esprit.tn.autoloc.Entities.CategorieVehicule;
import esprit.tn.autoloc.Entities.StatutVehicule;

import java.math.BigDecimal;

public record VehiculeResponse(Long idVehicule, String immatriculation, String marque, String modele,
                               CategorieVehicule categorie, BigDecimal tarifJournalier, StatutVehicule statut,
                               Long agenceId, String agenceNom) {}
