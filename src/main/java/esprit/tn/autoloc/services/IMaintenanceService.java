package esprit.tn.autoloc.services;

import esprit.tn.autoloc.Entities.Maintenance;
import java.util.List;

public interface IMaintenanceService {
    Maintenance addMaintenance(Maintenance maintenance);
    Maintenance updateMaintenance(Maintenance maintenance);
    void deleteMaintenance(Long id);
    Maintenance getMaintenanceById(Long id);
    List<Maintenance> getAllMaintenances();
}
