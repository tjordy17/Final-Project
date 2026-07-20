public class Main {

    public static void main(String[] args) {

        MedicalSystem medicalSystem = new MedicalSystem();

        MedicalInterface ui = new MedicalInterface(medicalSystem);

        ui.start();

    }

}