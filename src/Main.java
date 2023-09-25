import Dao.Impl.DepartmentDaoImpl;
import Dao.Impl.DoctorDaoImpl;
import Dao.Impl.HospitalDaoImpl;
import Dao.Impl.PatientDaoImpl;
import DateBase.DateBase;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import java.util.*;
import Enum.Gender;
import server.Impl.DepartmentServerImpl;
import server.Impl.DoctorServerImpl;
import server.Impl.HospitalServiceImpl;
import server.Impl.PatientServerImpl;

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
        Patient patient4 = new Patient(4L,"tunuk","abb",22, Gender.FEMALE);
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
        DateBase dateBase = new DateBase(hospitals,doctors);

        //DAO METHODS
        HospitalDaoImpl hospitalDao = new HospitalDaoImpl(dateBase);
        DepartmentDaoImpl departmentDao = new DepartmentDaoImpl(dateBase);
        DoctorDaoImpl doctorDao = new DoctorDaoImpl(dateBase);
        PatientDaoImpl patientDao = new PatientDaoImpl(dateBase);

        //Service METHODS
        HospitalServiceImpl hospitalService = new HospitalServiceImpl(hospitalDao);
        DepartmentServerImpl departmentServer = new DepartmentServerImpl(departmentDao);
        DoctorServerImpl doctorServer =new DoctorServerImpl(doctorDao);
        PatientServerImpl patientServer = new PatientServerImpl(patientDao);



        while (true) {
                System.out.println("Choose an action:");
            System.out.println("Hospital                                         Doctor" +
                    "\n1. Add a hospital                                9.  Find Doctor By Id" +
                    "\n2. Find a hospital by ID                         10. Assign Doctor To Department" +
                    "\n3. Get a list of all hospitals                   11. Get All Doctors By Hospital Id" +
                    "\n4. Get a list of patients from a hospital by ID  12. Get All Doctors By Department Id" +
                    "\n5. Delete a hospital by ID" +
                    "\n6. Get hospitals by address" +
                    "\n" +
                    "\nDepartment                                       Patient" +
                    "\n                                                 13. Add Patients To Hospital" +
                    "\n                                                 14. Get Patient By Id " +
                    "\n7. Get All Department By Hospital                15. Get Patient By Age " +
                    "\n8. Find Department By Name                       16. Sort Patients ByAge");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        // Hospital methods
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
                        scanner.nextLine();
                        hospitalService.findHospitalById(hospitalId);
                    case 3:
                        System.out.println(hospitalService.getAllHospital());
                        break;

                    case 4:
                        System.out.println("write the hospital id to receive patients ");
                        id = scanner.nextLong();
                        System.out.println(hospitalService.getAllPatientFromHospital(id));
                        break;

                    case 5:
                        System.out.println("Введите ID госпиталя для удаления:");
                        long deleteId = scanner.nextLong();
                        scanner.nextLine();
                        String deleteResult = hospitalService.deleteHospitalById(deleteId);
                        System.out.println(deleteResult);
                        break;

                    case 6:
                        System.out.println("Введите адрес для поиска госпиталей:");
                        String searchAddress = scanner.nextLine();
                        System.out.println(hospitalService.getAllHospitalByAddress(searchAddress));
                        break;
                    case 7:
                        // DEPARTMENT methods
                        System.out.println("Enter the hospital id to get the department");
                        id= scanner.nextLong();
                        System.out.println(departmentServer.getAllDepartmentByHospital(id));
                        scanner.nextLine();
                        break;
                    case 8:
                        System.out.println("Enter the name of the department to output it");
                        String departmentName= scanner.nextLine();
                        System.out.println(departmentServer.findDepartmentByName(departmentName));
                        break;
                    case 9:
                        //Doctor methods
                        System.out.println("Enter to search for a doctor by ID");
                        long doctorId = scanner.nextLong();
                        System.out.println(doctorServer.findDoctorById(doctorId));
                        break;
                    case 10:
                        System.out.println("Enter the department id");
                        long departmentId = scanner.nextLong();
                        List<Long>doctorID =new LinkedList<>(List.of(1L,2L,3L,4L));
                        System.out.println(doctorServer.assignDoctorToDepartment(departmentId, doctorID));
                        break;
                    case 11:
                        System.out.println("Enter the hospital id to find doctors");
                        hospitalId=scanner.nextLong();
                        System.out.println(doctorServer.getAllDoctorsByHospitalId(hospitalId));
                        break;
                    case 12:
                        System.out.println("Enter the department id");
                        departmentId= scanner.nextLong();
                        System.out.println(doctorServer.getAllDoctorsByDepartmentId(departmentId));
                        break;
                    case 13:
                        // Patient methods
                        System.out.println("Enter the hospital Id");
                        hospitalId= scanner.nextLong();
                        System.out.println("Enter the patient ID, ID 1-4 already exists");
                        long patientId = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Enter the patient's firstName ");
                        String patientName = scanner.nextLine();
                        System.out.println("Enter the patient's lastName ");
                        String patientName1 = scanner.nextLine();
                        System.out.println("Enter the patient's age");
                        int agePatient = scanner.nextInt();
                        System.out.println("Write your gender: ");
                        System.out.println(" Male : ");
                        System.out.println(" Female : ");
                        String genderSInput = scanner.next();
                        Gender gens = Gender.fromString(genderSInput);
                        if (gens != null) {
                            Patient newPatient = new Patient(patientId,patientName,patientName1,agePatient,gens);
                            System.out.println(newPatient);
                            List<Patient>newPatients = new LinkedList<>();
                            newPatients.add(newPatient);
                            patientServer.addPatientsToHospital(hospitalId,newPatients);
                        } else {
                            System.out.println("Invalid gender input.");
                        }
                        break;
                    case 14:
                        System.out.println("Enter the Id patient");
                        patientId = scanner.nextLong();
                        System.out.println(patientServer.getPatientById(patientId));
                        break;
                    case 15:
                        System.out.println(patientServer.getPatientByAge());
                        break;
                    case 16:
                        System.out.println("enter to get ascending - asc" +
                                "\nEnter to get descending-desc");
                        String sort= scanner.nextLine();
                        System.out.println(patientServer.sortPatientsByAge(sort));
                        break;
                    case 0:
                        System.out.println("Exit the program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Incorrect choice, try again.");
                        break;
                }
            }
        }
    }

