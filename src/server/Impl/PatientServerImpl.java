package server.Impl;

import Dao.Impl.PatientDaoImpl;
import model.Patient;
import server.IPatient;

import java.util.List;
import java.util.Map;

public class PatientServerImpl implements IPatient {
    private PatientDaoImpl patientDao;

    public PatientServerImpl(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        return patientDao.addPatientsToHospital(id ,patients);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public Map<Integer,List<Patient>>getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }
}
