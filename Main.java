import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with sample data
        

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerNewPatient();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    updateMedicalRecords();
                    break;
                case 4:
                    viewPatientRecords();
                    break;
                case 5:
                    searchPatients();
                    break;
                case 6:
                    generateReports();
                    break;
                case 7:
                    adminActions();
                    break;
                case 8:
                    System.out.println("Thank you for using the Medical Records Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        scanner.close();
    }
    public static void displayMenu() {
        System.out.println("\n========== Welcome to the Medical Records Management System ==========");
        System.out.println("1. Register a new patient");
        System.out.println("2. Schedule an appointment");
        System.out.println("3. Update Medical Records");
        System.out.println("4. View Patient Records");
        System.out.println("5. Search Patients");
        System.out.println("6. Generate Reports");
        System.out.println("7. Admin Actions");
        System.out.println("8. Exit");
        System.out.println("==============================================");
        System.out.println("Please select an option (1-8):");
    }

       private static int getIntInput(String prompt) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid integer.");
                scanner.nextLine(); // Clear buffer
            }
        }
        return value;
    }

private static void registerNewPatient() {
    //implementation for registering a new patient
}
private static void scheduleAppointment() {
    //implementation for scheduling an appointment
}
private static void updateMedicalRecords() {
    //implementation for updating medical records
}
private static void viewPatientRecords() {
    //implementation for viewing patient records
}
private static void searchPatients() {
    //implementation for searching patients
}
private static void generateReports() { 
    //implementation for generating reports
}
private static void adminActions() {
    //implementation for admin actions
}
}