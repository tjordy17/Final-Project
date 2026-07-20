import java.util.Scanner;

public class MedicalInterface {

    private MedicalSystem medicalSystem;
    private Scanner scanner;

    public MedicalInterface(MedicalSystem medicalSystem) {
        this.medicalSystem = medicalSystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        // Main application loop
    }

    private void displayMainMenu() {
        // Display menu options
    }

    private void patientMenu() {
        // Patient-related options
    }

    private void appointmentMenu() {
        // Appointment-related options
    }

    private void medicalRecordMenu() {
        // Medical record options
    }

    private void reportMenu() {
        // Report options
    }

    private int getChoice() {
        // Read user input
        return 0;
    }
}