package DateBase;

import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;

import java.util.List;

public class DateBase {
    private List<Hospital>hospitals;
    private List<Doctor>doctors;

    public DateBase(List<Hospital> hospitals, List<Doctor> doctors) {
        this.hospitals = hospitals;
        this.doctors = doctors;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @Override
    public String toString() {
        return "DateBase{" +
                "hospitals=" + hospitals +
                '}'+'\n';
    }
}
