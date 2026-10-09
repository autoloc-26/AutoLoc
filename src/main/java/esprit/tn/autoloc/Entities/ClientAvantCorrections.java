package esprit.tn.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * FICHIER DE DÉMONSTRATION - AVANT CORRECTIONS SONARQUBE
 * Ce fichier montre les problèmes détectés par SonarQube for IDE
 */
@Entity
@Table(name = "tableClient")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder @EqualsAndHashCode
public class ClientAvantCorrections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String numPermis;
    private LocalDate dateInscription;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Reservation> reservations = new ArrayList<>();
}
