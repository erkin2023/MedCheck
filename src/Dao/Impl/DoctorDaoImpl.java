package Dao.Impl;

import Dao.IDoctor;
import DateBase.DateBase;
import model.Department;
import model.Doctor;
import model.Hospital;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl implements IDoctor {
    private DateBase dateBase;

    public DoctorDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        List<Hospital> hospitals = dateBase.getHospitals();
        Optional<Doctor> found = Optional.empty();
        OUTER:
        for (Hospital h : hospitals) {
            for (Doctor d : h.getDoctors()) {
                if (id.equals(d.getId())) {
                    found = Optional.of(d);
                    break OUTER;
                }
            }
        }
        return found.orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        dateBase.getHospitals().stream().flatMap(hospital -> hospital.getDepartments().stream().filter(department -> department.getId().equals(departmentId)));

        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        List<Hospital> hospitals = dateBase.getHospitals();
            for (Hospital h : hospitals) {
                if (h.getId().equals(id)) {
                    return h.getDoctors();
                }
            }

        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        List<Hospital>hospitals=dateBase.getHospitals();
        for (Hospital h:hospitals){
            for(Department d:h.getDepartments()){
                if (d.getId().equals(id)){
                   return d.getDoctor();
                }
            }
        }
        return null;
    }
}
