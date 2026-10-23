package esprit.tn.autoloc.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tableAgence")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idAgence;
    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    @ToString.Exclude
    private List<Vehicule> vehicules = new ArrayList<>();

    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    @ToString.Exclude
    private List<Employe> employes = new ArrayList<>();
}