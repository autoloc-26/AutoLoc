package esprit.tn.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tableContrat")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idContrat;
    private LocalDate dateSignature;
    private BigDecimal montantTotal;
    private boolean valide;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("contrat-paiements")
    @Builder.Default
    @ToString.Exclude
    private List<Paiement> paiements = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Reservation reservation;
}