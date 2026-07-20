import java.util.ArrayList;

public class MedicalSystem {

    // Collections to store application data
    private ArrayList<Patient> patients;
    private ArrayList<Appointment> appointments;
    private ArrayList<MedicalRecord> medicalRecords;

    /**
     * Constructor
     */
    public MedicalSystem() {
        patients = new ArrayList<>();
        appointments = new ArrayList<>();
        medicalRecords = new ArrayList<>();
    }

    /* =====================================================
                      PATIENT MANAGEMENT
       ===================================================== */

    public boolean registerPatient(Patient patient) {
        // TODO: Implement after Patient.java is merged
        return false;
    }

    public Patient searchPatientByID(int patientId) {
        // TODO: Implement after Patient.java is merged
        return null;
    }

    public ArrayList<Patient> searchPatientByName(String name) {
        // TODO: Implement after Patient.java is merged
        return new ArrayList<>();
    }

    public boolean updatePatient(int patientId, Patient updatedPatient) {
        // TODO: Implement after Patient.java is merged
        return false;
    }

    public boolean deletePatient(int patientId) {
        // TODO: Implement after Patient.java is merged
        return false;
    }

    /* =====================================================
                   APPOINTMENT MANAGEMENT
       ===================================================== */

    public boolean scheduleAppointment(Appointment appointment) {
        // TODO: Implement after Appointment.java is merged
        return false;
    }

    public boolean updateAppointment(int appointmentId,
                                     Appointment updatedAppointment) {
        // TODO: Implement after Appointment.java is merged
        return false;
    }

    public boolean cancelAppointment(int appointmentId) {
        // TODO: Implement after Appointment.java is merged
        return false;
    }

    public ArrayList<Appointment> getAppointments(int patientId) {
        // TODO: Implement after Appointment.java is merged
        return new ArrayList<>();
    }

    /* =====================================================
                  MEDICAL RECORD MANAGEMENT
       ===================================================== */

    public boolean addMedicalRecord(MedicalRecord record) {
        // TODO: Implement after MedicalRecord.java is merged
        return false;
    }

    public ArrayList<MedicalRecord> getMedicalRecords(int patientId) {
        // TODO: Implement after MedicalRecord.java is merged
        return new ArrayList<>();
    }

    public boolean updateMedicalRecord(int patientId,
                                       MedicalRecord updatedRecord) {
        // TODO: Implement after MedicalRecord.java is merged
        return false;
    }

    /* =====================================================
                        REPORTS
       ===================================================== */

    public void generatePatientReport() {
        // TODO: Implement after all modules are integrated
    }

    public void generateAppointmentReport() {
        // TODO: Implement after all modules are integrated
    }

    public void generateMedicalHistoryReport() {
        // TODO: Implement after all modules are integrated
    }

    /* =====================================================
                     DATA PERSISTENCE
       ===================================================== */

    public void saveData() {
        // TODO: Save all collections to text files
    }

    public void loadData() {
        // TODO: Load all collections from text files
    }

    /* =====================================================
                     GETTERS (Optional)
       ===================================================== */

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public ArrayList<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }
}