package Dao.Impl;

import Dao.IDepartment;
import DateBase.DateBase;
import model.Department;
import model.Doctor;
import model.Hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentDaoImpl implements IDepartment {
    private DateBase dateBase;

    public DepartmentDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            Optional<List<Department>> foundDepartment = Optional.of(hospitals.stream()
                    .filter(h -> h.getId().equals(id))
                    .flatMap(hospital -> hospital.getDepartments().stream())
                    .collect(Collectors.toList()));
            return foundDepartment.orElseGet(ArrayList::new);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            Optional<List<Department>> foundDepartment = Optional.of(hospitals
                    .stream()
                    .flatMap(hospital -> hospital.getDepartments().stream())
                    .collect(Collectors.toList()));
            for (Department d : foundDepartment.orElse(Collections.emptyList())) {
                if (d.getDepartmentName().equals(name)) {
                    return d;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
