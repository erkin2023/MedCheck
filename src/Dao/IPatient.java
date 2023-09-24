package Dao;
import model.*;

import java.util.List;
import java.util.Map;

public interface IPatient {
    String addPatientsToHospital(Long id,List<Patient> patients);
    Patient getPatientById(Long id);
    Map<Integer, Patient> getPatientByAge();
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
