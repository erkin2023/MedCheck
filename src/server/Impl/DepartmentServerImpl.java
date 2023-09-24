package server.Impl;

import Dao.Impl.DepartmentDaoImpl;
import model.Department;
import server.IDepartment;
import java.util.List;

public class DepartmentServerImpl implements IDepartment {
    private DepartmentDaoImpl department;

    public DepartmentServerImpl(DepartmentDaoImpl department) {
        this.department = department;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return department.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return department.findDepartmentByName(name);
    }
}
