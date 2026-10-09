package esprit.tn.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * FICHIER DE DÉMONSTRATION - AVANT CORRECTIONS SONARQUBE
 * Ce fichier montre les problèmes détectés par SonarQube for IDE
 */
@Entity
@Table(name = "tableContrat")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder @EqualsAndHashCode
public class ContratAvantCorrections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContrat;
    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private boolean valide;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Paiement> paiements = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Reservation reservation;
}
