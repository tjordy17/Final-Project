public class Main {

    public static void main(String[] args) {

        MedicalSystem medicalSystem = new MedicalSystem();
        MedicalFrame medicalFrame = new MedicalFrame(medicalSystem);

        medicalFrame.setVisible(true);
    }
}