package ooplabfinal;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ClinicSystem {
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        initializeDoctors();
        appointments = FileHandler.loadAppointments();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    viewDoctors();
                    break;
                case 3:
                    bookAppointment(scanner);
                    break;
                case 4:
                    FileHandler.saveAppointments(appointments);
                    System.out.println("Exiting...");
                    return;
                case 5:
                    reviewOrSuggestion(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void initializeDoctors() {
        doctors.add(new GeneralPractitioner("Abu Hanif"));
        doctors.add(new Specialist("Jan Sami"));
        doctors.add(new GeneralPractitioner("Mohammed Alom"));
        doctors.add(new Specialist("Abu Bakkar Siddik"));
        doctors.add(new GeneralPractitioner("Mohammed Akram"));
        doctors.add(new Specialist("Jamila Khatun"));
        doctors.add(new GeneralPractitioner("Abdul Halim"));
        doctors.add(new Specialist("Abdul Karim"));
        doctors.add(new GeneralPractitioner("Abdul Mannan"));
        doctors.add(new Specialist("Abdul Majid"));
    }

    private static void displayMenu() {
        System.out.println("BELOW ARE OUR SERVICES:");
        System.out.println("1. Register Patient");
        System.out.println("2. View Doctors");
        System.out.println("3. Book Appointment");
        System.out.println("4. Exit (Hope you are satisfied with our services)");
        System.out.println("5. Review or Suggestion");
        System.out.print("Enter your choice: ");
    }

    private static void registerPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Patient patient = new Patient(name, age);
        System.out.println("Patient registered: " + patient.getName());
    }

    private static void viewDoctors() {
        for (Doctor doctor : doctors) {
            System.out.println("Name: " + doctor.getName());
            doctor.displayAvailability();
            System.out.println("===============");
        }
    }
    
    private static void bookAppointment(Scanner scanner) {
        System.out.println("Select doctor type type:");
        System.out.println("1. General Practitioner");
        System.out.println("2. Specialist");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            bookGeneralPractitionerAppointment(scanner);
        } else if (choice == 2) {
            bookSpecialistAppointment(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static void bookGeneralPractitionerAppointment(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Patient patient = new Patient(patientName, age);

        System.out.println("Select General Practitioner:");
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i) instanceof GeneralPractitioner) {
                System.out.println((i + 1) + ". " + doctors.get(i).getName());
            }
        }
        int doctorIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (doctorIndex < 0 || doctorIndex >= doctors.size() || !(doctors.get(doctorIndex) instanceof GeneralPractitioner)) {
            System.out.println("Invalid selection.");
            return;
        }

        Doctor selectedDoctor = doctors.get(doctorIndex);
        Appointment appointment = new Appointment(selectedDoctor, patient, new Date());
        appointments.add(appointment);
        System.out.println("Appointment booked with " + selectedDoctor.getName() + " for " + patient.getName());
        FileHandler.createAndWriteFile("General", patientName + ".txt", appointment.toString(), true);
    }

    private static void bookSpecialistAppointment(Scanner scanner) {
        System.out.print("Enter Specialist name: ");
        String specialistName = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();

        Specialist specialist = null;
        for (Doctor doctor : doctors) {
            if (doctor instanceof Specialist && doctor.getName().equalsIgnoreCase(specialistName)) {
                specialist = (Specialist) doctor;
                break;
            }
        }

        if (specialist == null) {
            System.out.println("Specialist not found.");
            return;
        }

        System.out.println("Specialist " + specialist.getName() + " is available on " + dateStr);
        System.out.println("Do you want to book an appointment? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter patient name: ");
            String patientName = scanner.nextLine();
            System.out.print("Enter patient age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            Patient patient = new Patient(patientName, age);
            Appointment appointment = new Appointment(specialist, patient, new Date());
            appointments.add(appointment);
            System.out.println("Appointment booked with " + specialist.getName() + " for " + patient.getName());
            FileHandler.createAndWriteFile("Specialist", patientName + ".txt", appointment.toString(), true);
        } else {
            System.out.println("Appointment not booked.");
        }
    }

    private static void reviewOrSuggestion(Scanner scanner) {
        System.out.println("1. Review a Doctor");
        System.out.println("2. Review the Clinic");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your review or suggestion: ");
        String review = scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter Doctor's name: ");
            String doctorName = scanner.nextLine();
            FileHandler.createAndWriteFile("Reviews", doctorName + ".txt", review, true);
        } else if (choice == 2) {
            FileHandler.createAndWriteFile("Reviews", "clinic.txt", review, true);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
