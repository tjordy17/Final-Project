public class Main {

    public static void main(String[] args) {

        // Create the system that manages all application data
        MedicalSystem medicalSystem = new MedicalSystem();

        // Create the command-line interface
        MedicalInterface medicalInterface = new MedicalInterface(medicalSystem);

        // Start the application
        medicalInterface.start();

    }
}