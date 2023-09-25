package Dao.Impl;

import Dao.IPatient;
import DateBase.DateBase;
import model.Patient;
import model.Hospital;

import java.util.*;
import java.util.stream.Collectors;

public class PatientDaoImpl implements IPatient {
    private DateBase dateBase;

    public PatientDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            Optional<Hospital> hospitalOptional = dateBase.getHospitals().stream()
                    .filter(h -> h.getId().equals(id))
                    .findFirst();
            if (hospitalOptional.isPresent()) {
                Hospital hospital = hospitalOptional.get();
                hospital.getPatients().addAll(patients);
                return patients.toString();
            } else {
                return "Больница с указанным ID не найдена.";
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception.
            return "Failed to add patients to hospital"; // Return an error message or handle the error as needed.
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return dateBase.getHospitals().stream()
                    .flatMap(hospital -> hospital.getPatients().stream())
                    .filter(patient -> patient.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it or throw a custom exception.
            return null; // Return null or handle the error as needed.
        }
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        try {
            List<Patient> allPatients = dateBase.getHospitals().stream()
                    .flatMap(hospital -> hospital.getPatients().stream())
                    .collect(Collectors.toList());

            return allPatients.stream().collect(Collectors.groupingBy(Patient::getAge));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            Comparator<Patient> ageComparator = Comparator.comparingInt(Patient::getAge);
            if ("desc".equalsIgnoreCase(ascOrDesc)) {
                ageComparator = ageComparator.reversed();
            }
            return dateBase.getHospitals().stream()
                    .flatMap(hospital -> hospital.getPatients().stream())
                    .sorted(ageComparator)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
