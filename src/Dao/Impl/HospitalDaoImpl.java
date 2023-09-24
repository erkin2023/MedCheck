package Dao.Impl;

import Dao.IHospital;
import model.Hospital;
import model.Patient;

import java.util.*;
import java.util.stream.Collectors;

import DateBase.DateBase;
public class HospitalDaoImpl implements IHospital {
   private DateBase dateBase;

    public HospitalDaoImpl(DateBase dateBase) {
        this.dateBase = dateBase;
    }

    @Override
    public String addHospital(Hospital hospital) {
        List<Hospital>hospitals = new LinkedList<>();
        hospitals.add(hospital);
        return "Added Hospital "+ hospitals;
    }

    @Override
    public Hospital findHospitalById(Long id) {
        List<Hospital>hospitals=dateBase.getHospitals();
        Optional<Hospital>findHospital=hospitals.stream().filter(h -> h.getId().equals(id)).findFirst();
        if (findHospital.isPresent()) {
            return findHospital.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Hospital> getAllHospital() {
        List<Hospital>hospitals=dateBase.getHospitals();
        if (!hospitals.isEmpty()) {
          return  dateBase.getHospitals();
        }else {
            return null;
        }
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        List<Hospital> hospitals = dateBase.getHospitals();
        if (!hospitals.isEmpty()){
            return hospitals.stream()
                    .filter(h -> h.getId().equals(id))
                    .flatMap(h -> h.getPatients().stream())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public String deleteHospitalById(Long id) {
        List<Hospital>hospitals=dateBase.getHospitals();
        if (hospitals.isEmpty()){
            Iterator<Hospital>iterator = hospitals.iterator();
            while (iterator.hasNext()){
                Hospital hospital =iterator.next();
                if (hospital.getId().equals(id)){
                    iterator.remove();
                    return "hospital deleted ";
                }
            }
        } return "Hospital by this ID not founded "+ id;
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        List<Hospital> hospitals = dateBase.getHospitals();
        Map<String, Hospital> hospitalMap = new HashMap<>();
        for (Hospital h : hospitals) {
            if (h.getAddress().equals(address)) {
                hospitalMap.put(address, h);
            }
        }
        if (!hospitalMap.isEmpty()) {
            return hospitalMap;
        } else {
            return null;
        }
    }
}
