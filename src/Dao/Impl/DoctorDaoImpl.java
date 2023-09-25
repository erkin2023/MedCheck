package Dao.Impl;

import Dao.IDoctor;
import DateBase.DateBase;
import model.Department;
import model.Doctor;
import model.Hospital;

import java.util.*;

public class DoctorDaoImpl implements IDoctor {
    private DateBase dateBase;
    private DoctorDaoImpl doctorDao;

    public DoctorDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorIds) {
        try {
            for (Hospital hospital : dateBase.getHospitals()) {
                List<Department> departments = hospital.getDepartments();
                for (Department dd : departments) {
                    if (dd.getId() == departmentId) {
                        List<Doctor> doctors = dd.getDoctor();
                        List<Doctor> doctorsToAdd = new ArrayList<>();
                        List<Long> missingDoctorIds = new ArrayList<>();
                        for (Long doctorId : doctorIds) {
                            boolean doctorFound = false;
                            for (Doctor doctor : doctors) {
                                if (doctor.getId() == doctorId) {
                                    doctorsToAdd.add(doctor);
                                    doctorFound = true;
                                    break;
                                }
                            }
                            if (!doctorFound) {
                                missingDoctorIds.add(doctorId);
                            }
                        }
                        if (!missingDoctorIds.isEmpty()) {
                            throw new NoSuchElementException("Doctors with IDs " + missingDoctorIds + " not found");
                        }
                        // Add the collected doctors to the department
                        dd.getDoctor().addAll(doctorsToAdd);
                        return "Doctors assigned to department successfully";
                    }
                }
            }
            return "Department not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to assign doctors to department";
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            for (Hospital h : hospitals) {
                if (h.getId().equals(id)) {
                    return h.getDoctors();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            for (Hospital h : hospitals) {
                for (Department d : h.getDepartments()) {
                    if (d.getId().equals(id)) {
                        return d.getDoctor();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
