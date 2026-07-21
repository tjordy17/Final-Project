public class MedicalRecord {

    private String diagnosis;
    private String treatment;
    private String medication;
    private String notes;

    public MedicalRecord(String diagnosis,String treatment,String medication,String notes){
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medication = medication;
        this.notes = notes;
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
        return String.format("{%s,%s,%s,%s}",diagnosis, treatment, medication, notes);
    }

}