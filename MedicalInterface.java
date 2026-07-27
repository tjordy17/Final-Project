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
        System.out.println("4. Show All Patients in database");
        System.out.println("5. Back");

        int choice = getChoice();

        switch (choice) {

            case 1:
                System.out.println("First Name: ");
                String first_name = scanner.nextLine();

                System.out.println("Last Name: ");
                String last_name = scanner.nextLine();

                System.out.println("Date of Birth dd/mm/yyyy: ");
                String date_of_birth = scanner.nextLine();

                System.out.println("Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Gender: ");
                String gender = scanner.nextLine();

                System.out.println("Phone Number: ");
                String phone_number = scanner.nextLine();

                medicalSystem.registerPatient(new Patient(
                    0,
                    first_name,
                    last_name,
                    date_of_birth, 
                    age, 
                    gender, 
                    phone_number
                ));
                // medicalSystem.registerPatient(...);
                break;

            case 2:
                System.out.println("1: Search by Name \n 2: Search by Id");
                System.out.println("\nChoice: ");
                int choice2 = scanner.nextInt();
                scanner.nextLine();

                if(choice2 == 1){
                    System.out.println("First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.println("Last Name: ");
                    String lastName = scanner.nextLine();

                    try{
                        Patient tempPatient = medicalSystem.searchPatientByName(firstName, lastName);
                        System.out.println(tempPatient.getInfo());
                    }
                    catch(PatientNotFoundException e){
                        System.out.println("Patiant with the name: '" + firstName + " " + lastName + "'" + "not found");
                        break;
                    }


                }
                else if(choice2 == 2){
                    System.out.println("1: Search by Name \n 2: Search by Id");
                    System.out.println("\nChoice: ");
                    int id = scanner.nextInt();

                    try {
                        Patient tempPatient = medicalSystem.searchPatientByID(id);
                        System.out.println(tempPatient.getInfo());
                    }
                    catch(PatientNotFoundException e){
                        System.out.println("Patiant with the id: '" + id + "'" + " not found");
                        break;
                    }

                }
                else{
                    System.out.println("Please try again and pick 1 or 2");
                }

                break;

            case 3:
                System.out.println("Update Patient (TODO)");
                break;

            case 4:
                String output = medicalSystem.getStringOfAllPatients();
                System.out.println(output);
                break;
            
            case 5:
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
                System.out.println("Schedule Appointment (TODO)");
                break;

            case 2:
                System.out.println("Update Appointment (TODO)");
                break;

            case 3:
                System.out.println("Cancel Appointment (TODO)");
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

                MedicalRecord medicalRecord;

                System.out.println("Id: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                
                System.out.println("Diagnosis: ");
                String diagnosis = scanner.nextLine();

                System.out.println("Treatment Plan: ");
                String treatment = scanner.nextLine();

                System.out.println("Medication: ");
                String medication = scanner.nextLine();

                System.out.println("Notes: ");
                String notes = scanner.nextLine();

                medicalRecord = new MedicalRecord(diagnosis, treatment, medication, notes);

                medicalSystem.addMedicalRecord(id,medicalRecord);
                break;

            case 2:
                System.out.println("View Medical Record (TODO)");
                break;

            case 3:
                System.out.println("Update Medical Record (TODO)");
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
                System.out.println("Patient Report (TODO)");
                break;

            case 2:
                System.out.println("Appointment Report (TODO)");
                break;

            case 3:
                System.out.println("Medical History Report (TODO)");
                break;

            case 4:
                return;

            default:
                System.out.println("Invalid option.");
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
}