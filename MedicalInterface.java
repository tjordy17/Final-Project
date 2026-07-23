import java.util.ArrayList;
import java.util.Scanner;

public class MedicalInterface {

    private MedicalSystem medicalSystem;
    private Scanner scanner;

    public MedicalInterface(MedicalSystem medicalSystem) {
        this.medicalSystem = medicalSystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        System.out.println("=======================================");
        System.out.println("   Medical Records Management System");
        System.out.println("=======================================");

        while (running) {

            displayMainMenu();

            int choice = getChoice();

            switch (choice) {

                case 1:
                    patientMenu();
                    break;

                case 2:
                    appointmentMenu();
                    break;

                case 3:
                    medicalRecordMenu();
                    break;

                case 4:
                    reportMenu();
                    break;

                case 5:
                    System.out.println("\nSaving data...");
                    medicalSystem.saveData();
                    break;

                case 6:
                    System.out.println("\nLoading data...");
                    medicalSystem.loadData();
                    break;

                case 7:
                    System.out.println("\nExiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private void displayMainMenu() {

        System.out.println("\n========== Main Menu ==========");

        System.out.println("1. Patient Management");
        System.out.println("2. Appointment Management");
        System.out.println("3. Medical Records");
        System.out.println("4. Reports");
        System.out.println("5. Save Data");
        System.out.println("6. Load Data");
        System.out.println("7. Exit");

        System.out.println("===============================");
    }

    private void patientMenu() {

        System.out.println("\n--- Patient Management ---");

        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Back");

        int choice = getChoice();

        switch (choice) {

            case 1:
                registerPatient();
                break;

            case 2:
                searchPatient();
                break;

            case 3:
                updatePatient();
                break;

            case 4:
                return;

            default:
                System.out.println("Invalid option.");
        }
    }

    private void appointmentMenu() {

        System.out.println("\n--- Appointment Management ---");

        System.out.println("1. Schedule Appointment");
        System.out.println("2. Update Appointment");
        System.out.println("3. Cancel Appointment");
        System.out.println("4. Back");

        int choice = getChoice();

        switch (choice) {

            case 1:
                scheduleAppointment();
                break;

            case 2:
                updateAppointment();
                break;

            case 3:
                cancelAppointment();
                break;

            case 4:
                return;

            default:
                System.out.println("Invalid option.");
        }
    }

    private void medicalRecordMenu() {

        System.out.println("\n--- Medical Records ---");

        System.out.println("1. Add Record");
        System.out.println("2. View Record");
        System.out.println("3. Update Record");
        System.out.println("4. Back");

        int choice = getChoice();

        switch (choice) {

            case 1:
                addMedicalRecord();
                break;

            case 2:
                viewMedicalRecords();
                break;

            case 3:
                updateMedicalRecord();
                break;

            case 4:
                return;

            default:
                System.out.println("Invalid option.");
        }
    }

    private void reportMenu() {

        System.out.println("\n--- Reports ---");

        System.out.println("1. Patient Report");
        System.out.println("2. Appointment Report");
        System.out.println("3. Medical History Report");
        System.out.println("4. Back");

        int choice = getChoice();

        switch (choice) {

            case 1:
                medicalSystem.generatePatientReport();
                break;

            case 2:
                medicalSystem.generateAppointmentReport();
                break;

            case 3:
                medicalSystem.generateMedicalHistoryReport();
                break;

            case 4:
                return;

            default:
                System.out.println("Invalid option.");
        }
    }

    private void registerPatient() {
        int patientId = readInt("Patient ID: ");
        String firstName = readRequiredText("First name: ");
        String lastName = readRequiredText("Last name: ");
        String dateOfBirth = readRequiredText("Date of birth: ");
        int age = readInt("Age: ");
        String gender = readRequiredText("Gender: ");
        String phone = readRequiredText("Phone: ");

        Patient patient = new Patient(patientId, firstName, lastName, dateOfBirth, age, gender, phone);

        if (medicalSystem.registerPatient(patient)) {
            System.out.println("Patient registered successfully.");
        } else {
            System.out.println("Patient registration failed. ID may already exist.");
        }
    }

    private void searchPatient() {
        System.out.println("1. Search by ID");
        System.out.println("2. Search by name");
        int searchChoice = getChoice();

        if (searchChoice == 1) {
            int patientId = readInt("Patient ID: ");
            Patient patient = medicalSystem.searchPatientByID(patientId);

            if (patient == null) {
                System.out.println("Patient not found.");
            } else {
                System.out.println(patient);
            }
        } else if (searchChoice == 2) {
            String name = readRequiredText("Enter name fragment: ");
            ArrayList<Patient> patients = medicalSystem.searchPatientByName(name);

            if (patients.isEmpty()) {
                System.out.println("No matching patients found.");
            } else {
                for (Patient patient : patients) {
                    System.out.println(patient);
                }
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private void updatePatient() {
        int patientId = readInt("Patient ID to update: ");
        Patient existingPatient = medicalSystem.searchPatientByID(patientId);

        if (existingPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String firstName = readRequiredText("New first name (leave blank to keep current): ");
        String lastName = readRequiredText("New last name (leave blank to keep current): ");
        int age = readInt("New age (enter 0 to keep current): ");
        String gender = readRequiredText("New gender (leave blank to keep current): ");
        String phone = readRequiredText("New phone (leave blank to keep current): ");

        if (!firstName.isEmpty()) {
            existingPatient.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            existingPatient.setLastName(lastName);
        }
        if (age != 0) {
            existingPatient.setAge(age);
        }
        if (!gender.isEmpty()) {
            existingPatient.setGender(gender);
        }
        if (!phone.isEmpty()) {
            existingPatient.setPhone(phone);
        }

        if (medicalSystem.updatePatient(patientId, existingPatient)) {
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient update failed.");
        }
    }

    private void scheduleAppointment() {
        int appointmentId = readInt("Appointment ID: ");
        int patientId = readInt("Patient ID: ");
        String doctor = readRequiredText("Doctor: ");
        String date = readRequiredText("Date: ");
        String time = readRequiredText("Time: ");

        Appointment appointment = new Appointment(appointmentId, patientId, doctor, date, time);

        if (medicalSystem.scheduleAppointment(appointment)) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Appointment scheduling failed. Check patient ID or duplicate appointment ID.");
        }
    }

    private void updateAppointment() {
        int appointmentId = readInt("Appointment ID to update: ");
        String doctor = readRequiredText("New doctor (leave blank to keep current): ");
        String date = readRequiredText("New date (leave blank to keep current): ");
        String time = readRequiredText("New time (leave blank to keep current): ");

        Appointment updatedAppointment = new Appointment(appointmentId, -1, doctor, date, time);

        if (medicalSystem.updateAppointment(appointmentId, updatedAppointment)) {
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Appointment update failed.");
        }
    }

    private void cancelAppointment() {
        int appointmentId = readInt("Appointment ID to cancel: ");

        if (medicalSystem.cancelAppointment(appointmentId)) {
            System.out.println("Appointment cancelled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private void addMedicalRecord() {
        int patientId = readInt("Patient ID: ");
        String diagnosis = readRequiredText("Diagnosis: ");
        String treatment = readRequiredText("Treatment: ");
        String medication = readRequiredText("Medication: ");
        String notes = readRequiredText("Notes: ");

        MedicalRecord record = new MedicalRecord(patientId, diagnosis, treatment, medication, notes);

        if (medicalSystem.addMedicalRecord(record)) {
            System.out.println("Medical record added successfully.");
        } else {
            System.out.println("Medical record could not be added. Check the patient ID.");
        }
    }

    private void viewMedicalRecords() {
        int patientId = readInt("Patient ID: ");
        ArrayList<MedicalRecord> records = medicalSystem.getMedicalRecords(patientId);

        if (records.isEmpty()) {
            System.out.println("No medical records found for this patient.");
            return;
        }

        for (MedicalRecord record : records) {
            System.out.println(record);
        }
    }

    private void updateMedicalRecord() {
        int patientId = readInt("Patient ID: ");
        ArrayList<MedicalRecord> records = medicalSystem.getMedicalRecords(patientId);

        if (records.isEmpty()) {
            System.out.println("No medical records found for this patient.");
            return;
        }

        String diagnosis = readRequiredText("New diagnosis: ");
        String treatment = readRequiredText("New treatment: ");
        String medication = readRequiredText("New medication: ");
        String notes = readRequiredText("New notes: ");

        MedicalRecord updatedRecord = new MedicalRecord(patientId, diagnosis, treatment, medication, notes);

        if (medicalSystem.updateMedicalRecord(patientId, updatedRecord)) {
            System.out.println("Medical record updated successfully.");
        } else {
            System.out.println("Medical record update failed.");
        }
    }

    private int getChoice() {

        while (true) {

            try {

                System.out.print("\nEnter choice: ");

                return Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");

            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}