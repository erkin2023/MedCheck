package Dao;
import model.*;

import java.util.List;

public interface IDoctor {
    Doctor findDoctorById(Long id);

    // Department‘ти id менен табып, анан Doctor‘лорду листтеги айдилери менен табып анан ошол табылган Department'ге табылган Doctor'лорду кошуп коюнунуз
    String assignDoctorToDepartment(Long departmentId,List<Long>doctorId);
    List<Doctor> getAllDoctorsByHospitalId(Long id);
    List<Doctor> getAllDoctorsByDepartmentId(Long id);
}
