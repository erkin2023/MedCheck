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

    public String addHospital(Hospital hospital) {
        try {
            List<Hospital> hospitals = new LinkedList<>();
            hospitals.add(hospital);
            return "Added Hospital " + hospitals;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add hospital";
        }
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            Optional<Hospital> findHospital = hospitals.stream().filter(h -> h.getId().equals(id)).findFirst();
            return findHospital.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Hospital> getAllHospital() {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            return hospitals.isEmpty() ? new ArrayList<>() : hospitals;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            if (!hospitals.isEmpty()) {
                return hospitals.stream()
                        .filter(h -> h.getId().equals(id))
                        .flatMap(h -> h.getPatients().stream())
                        .collect(Collectors.toList());
            }
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            if (!hospitals.isEmpty()) {
                Iterator<Hospital> iterator = hospitals.iterator();
                while (iterator.hasNext()) {
                    Hospital hospital = iterator.next();
                    if (hospital.getId().equals(id)) {
                        iterator.remove();
                        return "Hospital deleted";
                    }
                }
            }
            return "Hospital by this ID not found " + id;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete hospital";
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            List<Hospital> hospitals = dateBase.getHospitals();
            Map<String, Hospital> hospitalMap = new HashMap<>();
            for (Hospital h : hospitals) {
                if (h.getAddress().equals(address)) {
                    hospitalMap.put(address, h);
                }
            }
            return hospitalMap.isEmpty() ? null : hospitalMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
