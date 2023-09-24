package Dao.Impl;

import Dao.IPatient;
import DateBase.DateBase;
import model.Patient;
import model.Hospital;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PatientDaoImpl implements IPatient {
    private DateBase dateBase;

    public PatientDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
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
    }

    @Override
    public Patient getPatientById(Long id) {
        return dateBase.getHospitals().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        Optional<Patient> patientOptional = dateBase.getHospitals().stream().flatMap(hospital -> hospital.getPatients().stream()).findFirst();
        return patientOptional.map(patient -> Map.of(patient.getAge(), patient))
                .orElse(Map.of());
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        Comparator<Patient>ageComparator =Comparator.comparingInt(Patient::getAge);
        if ("desc".equalsIgnoreCase(ascOrDesc)) {
            ageComparator = ageComparator.reversed();
        }
        return dateBase.getHospitals().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .sorted(ageComparator)
                .collect(Collectors.toList());
    }
}
