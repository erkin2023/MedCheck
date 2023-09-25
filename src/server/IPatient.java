package server;

import model.Patient;

import java.util.List;
import java.util.Map;

public interface IPatient {
    String addPatientsToHospital(Long id,List<Patient> patients);
    Patient getPatientById(Long id);
    Map<Integer,List<Patient>> getPatientByAge();
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
