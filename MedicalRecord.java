public class MedicalRecord {

    private int patientID;
    private String diagnosis;
    private String treatment;
    private String medication;
    private String notes;

    public MedicalRecord(int patientID, String diagnosis, String treatment, String medication, String notes) {
        this.patientID = patientID;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medication = medication;
        this.notes = notes;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getDiagnosis(){
        return diagnosis;
    }

    public String getTreatment(){
        return treatment;
    }

    public String getMedication(){
        return medication;
    }

    public String getNotes(){
        return notes;
    }

    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment){
        this.treatment = treatment;
    }

    public void setMedication(String medication){
        this.medication = medication;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public String toString(){
        return String.format("{%d,%s,%s,%s,%s}", patientID, diagnosis, treatment, medication, notes);
    }

}