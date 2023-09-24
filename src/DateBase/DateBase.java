package DateBase;

import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;

import java.util.List;

public class DateBase {
    private List<Hospital>hospitals;

    public DateBase(List<Hospital> hospitals) {
        this.hospitals = hospitals;
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
