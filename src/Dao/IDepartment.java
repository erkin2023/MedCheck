package Dao;
import model.*;
import java.util.List;

public interface IDepartment {
    List<Department> getAllDepartmentByHospital(Long id);
    Department findDepartmentByName(String name);
}
