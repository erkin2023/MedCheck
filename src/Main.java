import Dao.Impl.DepartmentDaoImpl;
import Dao.Impl.HospitalDaoImpl;
import DateBase.DateBase;
import com.sun.security.jgss.GSSUtil;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;

import java.util.*;

import Enum.Gender;
import server.Impl.DepartmentServerImpl;
import server.Impl.HospitalServiceImpl;

public class Main {
    public static void main(String[] args) {
        //Scanner
        Scanner scanner = new Scanner(System.in);


        Patient patient1 = new Patient(1L,"agerim","kyzy",18, Gender.FEMALE);
        Patient patient2 = new Patient(2L,"adela","auw",20, Gender.FEMALE);
        // Patient list
        List<Patient>patients = new LinkedList<>();
        patients.add(patient1);
        patients.add(patient2);
        List<Patient>patients2 = new LinkedList<>();
        Patient patient3 = new Patient(3L,"alina","avv",21, Gender.FEMALE);
        Patient patient4 = new Patient(3L,"tunuk","abb",22, Gender.FEMALE);
        patients2.add(patient3);
        patients2.add(patient4);

        // Doctor list
        List<Doctor>doctors = new LinkedList<>();
        Doctor doctor1 = new Doctor(1L,"erkin","toigonbaev",Gender.MALE,5);
        Doctor doctor2 = new Doctor(2L,"Jandar","taalaybkov",Gender.MALE,3);
        doctors.add(doctor1);
        doctors.add(doctor2);
        // Doctor list
        List<Doctor>doctors2 = new LinkedList<>();
        Doctor doctor3 = new Doctor(3L,"harli","kvin",Gender.FEMALE,2);
        Doctor doctor4 = new Doctor(4L,"robby","margo",Gender.FEMALE,4);
        doctors.add(doctor3);
        doctors.add(doctor4);

        // Department list
        List<Department>departments = new LinkedList<>();
        Department department1 = new Department(1L,"surgery",doctors);
        departments.add(department1);
        List<Department>departments2 = new LinkedList<>();
        Department department2 = new Department(2L,"psychotherapy",doctors2);
        departments2.add(department2);

        // Hospital list
        List<Hospital>hospitals = new LinkedList<>();
        Hospital hospital1 = new Hospital(1L,"bishkek","chuy150",departments,doctors,patients);
        Hospital hospital2 = new Hospital(2L,"bishkek.psychotherapy","manas170",departments2,doctors2,patients2);
        hospitals.add(hospital1);
        hospitals.add(hospital2);

        //DATE_BASE
        DateBase dateBase = new DateBase(hospitals);

        //DAO METHODS
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(dateBase);
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(dateBase);

        //Service METHODS
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        DepartmentServerImpl departmentServer = new DepartmentServerImpl(departmentDao);



        while (true) {
            while (true) {
                System.out.println("Choose an action:");
                System.out.println("1. Add a hospital                                    7. Get All Department By Hospital");
                System.out.println("2. Find a hospital by ID");
                System.out.println("3. Get a list of all hospitals");
                System.out.println("4. Get a list of patients from a hospital by ID");
                System.out.println("5. Delete a hospital by ID");
                System.out.println("6. Get hospitals by address");
                System.out.println("0. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println();
                        System.out.println("write the hospital id from 1 to 2 already exists ");
                        long id = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("write the name of the hospital");
                        String nameHospital = scanner.nextLine();

                        System.out.println("write the address of the hospital");
                        String addressHospital = scanner.nextLine();
                        System.out.println(hospitalService.addHospital(new Hospital(id, nameHospital, addressHospital, new LinkedList<>(), new LinkedList<>(), new LinkedList<>())));
                        break;

                    case 2:
                        System.out.println("Введите ID госпиталя для поиска:");
                        long hospitalId = scanner.nextLong();
                        scanner.nextLine(); // Consume the newline character.
                        Hospital foundHospital = hospitalDao.findHospitalById(hospitalId);
                        if (foundHospital != null) {
                            System.out.println("Найден госпиталь: " + foundHospital);
                        } else {
                            System.out.println("Госпиталь не найден.");
                        }
                        break;

                    case 3:
                        // Получить список всех госпиталей и вывести на экран.
                        System.out.println(hospitalDao.getAllHospital());
                        break;

                    case 4:
                        // Получить список пациентов из госпиталя по ID и вывести на экран.
                        System.out.println("write the hospital id to receive patients ");
                        id = scanner.nextLong();
                        System.out.println(hospitalDao.getAllPatientFromHospital(id));
                        break;

                    case 5:
                        System.out.println("Введите ID госпиталя для удаления:");
                        long deleteId = scanner.nextLong();
                        scanner.nextLine(); // Consume the newline character.
                        String deleteResult = hospitalDao.deleteHospitalById(deleteId);
                        System.out.println(deleteResult);
                        break;

                    case 6:
                        System.out.println("Введите адрес для поиска госпиталей:");
                        String searchAddress = scanner.nextLine();
                        System.out.println(hospitalDao.getAllHospitalByAddress(searchAddress));

                    case 7:
                        // DEPARTMENT
                        System.out.println("Enter the hospital id to get the department");
                        id= scanner.nextLong();
                        departmentServer.getAllDepartmentByHospital(id);
                    case 0:
                        System.out.println("Выход из программы.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Некорректный выбор, попробуйте еще раз.");
                        break;
                }
            }
        }
    }
}
