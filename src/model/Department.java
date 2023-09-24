package model;

import java.util.List;

public class Department {
    private Long id;
    private String departmentName;
    private List<Doctor> doctors;

    public Department(Long id, String departmentName, List<Doctor> doctor) {
        this.id = id;
        this.departmentName = departmentName;
        this.doctors = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Doctor> getDoctor() {
        return doctors;
    }

    public void setDoctor(List<Doctor> doctor) {
        this.doctors = doctor;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", doctor=" + doctors +
                '}';
    }
}
