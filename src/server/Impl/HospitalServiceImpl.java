package server.Impl;

import Dao.Impl.HospitalDaoImpl;
import model.Hospital;
import model.Patient;
import server.IHospital;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements IHospital {
    private HospitalDaoImpl hospitalDaoImpl;

    public HospitalServiceImpl(HospitalDaoImpl hospitalDaoImpl) {
        this.hospitalDaoImpl = hospitalDaoImpl;
    }

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDaoImpl.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDaoImpl.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDaoImpl.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDaoImpl.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDaoImpl.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDaoImpl.getAllHospitalByAddress(address);
    }
}
