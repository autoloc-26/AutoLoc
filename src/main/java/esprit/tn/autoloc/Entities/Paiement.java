package esprit.tn.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tablePaiement")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idPaiement;
    private BigDecimal montant;
    private LocalDate datePaiement;
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("contrat-paiements")
    @ToString.Exclude
    private Contrat contrat;
}