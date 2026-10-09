package esprit.tn.autoloc.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tableEmploye")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idEmploye;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    private RoleEmploye role;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Agence agence;
}