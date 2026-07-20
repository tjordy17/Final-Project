public class MedicalSystem {

    // Patient

    public boolean registerPatient(Patient patient);

    public Patient searchPatientByID(int id);

    public ArrayList<Patient> searchPatientByName(String name);

    public boolean updatePatient(int id, Patient updatedPatient);

    public boolean deletePatient(int id);



    // Medical Records

    public boolean addMedicalRecord(int patientId, MedicalRecord record);

    public ArrayList<MedicalRecord> getMedicalRecords(int patientId);

    public boolean updateMedicalRecord(int patientId, MedicalRecord record);



    // Appointments

    public boolean scheduleAppointment(Appointment appointment);

    public boolean updateAppointment(int appointmentId,Appointment updatedAppointment);

    public boolean cancelAppointment(int appointmentId);

    public ArrayList<Appointment> getAppointments(int patientId);



    // Reports

    public void generatePatientReport();

    public void generateAppointmentReport();

    public void generateMedicalHistoryReport();



    // Persistence

    public void loadData();

    public void saveData();

}