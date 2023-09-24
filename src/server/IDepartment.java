package server;

import model.Department;

import java.util.List;

public interface IDepartment {
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);
}
