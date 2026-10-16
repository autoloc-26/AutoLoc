package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Employe;
import java.util.List;

public interface IEmployeService {
    Employe addEmploye(Employe employe);
    Employe updateEmploye(Employe employe);
    void deleteEmploye(Long id);
    Employe getEmployeById(Long id);
    List<Employe> getAllEmployes();
}
