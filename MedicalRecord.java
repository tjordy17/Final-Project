public class MedicalRecord {

    public MedicalRecord(int patientId,String diagnosis,String treatment,String medication,String notes);

    public int getPatientID();

    public String getDiagnosis();

    public String getTreatment();

    public String getMedication();

    public String getNotes();

    public void setDiagnosis(String diagnosis);

    public void setTreatment(String treatment);

    public void setMedication(String medication);

    public void setNotes(String notes);

    public String toString();

}