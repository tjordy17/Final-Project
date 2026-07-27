public class MedicalRecord {

    private int patientID;
    private String diagnosis;
    private String treatment;
    private String medication;
    private String notes;

    public MedicalRecord(String diagnosis, String treatment, String medication, String notes) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.medication = medication;
        this.notes = notes;
    }

    //GETTERS

    //Gets the diagnosis
    public String getDiagnosis(){
        return diagnosis;
    }

    //Gets the treatment
    public String getTreatment(){
        return treatment;
    }

    //Gets the medication
    public String getMedication(){
        return medication;
    }

    //Gets the notes
    public String getNotes(){
        return notes;
    }

    //Sets the diafnosis
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    //Sets the treatment
    public void setTreatment(String treatment){
        this.treatment = treatment;
    }

    //Sets the medication
    public void setMedication(String medication){
        this.medication = medication;
    }

    //Sets the notes
    public void setNotes(String notes){
        this.notes = notes;
    }

    //Gets the format for ouputing to a file and internal commands and programming
    public String toString(){
        return String.format("{%s,%s,%s,%s}", diagnosis, treatment, medication, notes);
    }

    //Formats it for human readable
    public String formatReadable(){
        return String.format("""
            
                Diagnosis: %s
                Treatment Plan: %s
                Medication: %s
                notes: %s

                """,diagnosis,treatment,medication,notes);
    }

}