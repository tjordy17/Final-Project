import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Painter;

public class MedicalSystem {

    private int intergerTracker = 0;
    

    // Collections to store application data
    private Hashtable<Integer, Patient> patients;
    private ArrayList<Appointment> appointments;

    /**
     * Constructor
     */
    public MedicalSystem() {
        patients = new Hashtable<Integer, Patient>();
        appointments = new ArrayList<>();
    }

    /* =====================================================
                      PATIENT MANAGEMENT
       ===================================================== */

    public String getStringOfAllPatients(){
        String out = "\n";
        for (int patientId : patients.keySet()) {
            out += patientId + ":" + patients.get(patientId).getFirstName() + " " + patients.get(patientId).getLastName() + "\n";
        }
        return out;
    }

    public boolean registerPatient(Patient patient) {
        intergerTracker++;
        patient.Id = intergerTracker;
        patients.put(intergerTracker, patient);

        System.out.println("Succesfully created patient with id: " + intergerTracker);
        
        return false;
    }

    public Patient searchPatientByID(int patientId) {
        Patient pTemp;
        pTemp = patients.get(patientId);
        if(pTemp == null){
            throw new PatientNotFoundException();
        }
        return pTemp;
    }

    public Patient searchPatientByName(String firstName, String lastName) {
        Patient pTemp = null;
        for (Patient patient : patients.values()) {
            if(patient.getFirstName() == firstName && patient.getLastName() == lastName){
                pTemp = patient;
            }
        }
        if(pTemp == null){
            throw new PatientNotFoundException();
        }
        return pTemp;
    }

    public boolean updatePatient(int patientId, Patient updatedPatient) {
        // TODO: Implement after Patient.java is merged
        return false;
    }

    public boolean deletePatient(int patientId) {
        Patient pTemp = patients.remove(patientId);
        if(pTemp == null){
            throw new PatientNotFoundException();
        }
        // TODO: Implement after Patient.java is merged
        return true;
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

    public boolean addMedicalRecord(int patientId, MedicalRecord record) {
        try {
            searchPatientByID(patientId).addToRecord(record);
        } 
        catch (PatientNotFoundException e) {
            System.out.println("Patient with the id: " + patientId + " was not found");
            return false;
        }
        return true;
     
    }

    public ArrayList<MedicalRecord> getMedicalRecords(int patientId) {
        try {
            return searchPatientByID(patientId).getMedicalRecords();
        } 
        catch (PatientNotFoundException e) {
            System.out.println("Patient with the id: " + patientId + " was not found");
            return null;
        }
         
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

        //Patient Saving
        
        File patientsFile =  new File("patients.txt");

        BufferedWriter bf = null;

        try {
            bf = new BufferedWriter(new FileWriter(patientsFile));

            bf.write("" + intergerTracker);
            bf.newLine();

            for(Map.Entry<Integer, Patient> entry : patients.entrySet() ){

                bf.write(entry.getKey() + ":" + entry.getValue());

                bf.newLine();
            }

            bf.flush();

        }
        catch(IOException e){
            System.out.println(e);
        }
        finally{
            try{
                if(bf!=null){
                    bf.close();
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

    }

    public void loadData() {
        // TODO: Load all collections from text files

        //Load Patient Data
        File patientsFile =  new File("patients.txt");

        BufferedReader bf = null;


        try {


            bf = new BufferedReader(new FileReader(patientsFile));  

            String top = bf.readLine();
            intergerTracker = Integer.parseInt(top);

            while(bf.ready()){
                String line = bf.readLine();

                if(line == null){
                    continue;
                }

                String[] keyValue = line.split(":",2);

                int key = Integer.parseInt(keyValue[0]);

                String[] patiantValues = keyValue[1].split(",",7);

                Patient tempPatient = new Patient(key, patiantValues[0], 
                    patiantValues[1], 
                    patiantValues[2], 
                    Integer.parseInt(patiantValues[3]), 
                    patiantValues[4], 
                    patiantValues[5]);
                
                Pattern pattern = Pattern.compile("\\{([^}]*)\\}");
                Matcher matcher = pattern.matcher(line);
      
                while(matcher.find()){
                    String[] recordInfo = matcher.group(1).split(",");

                    tempPatient.addToRecord(new MedicalRecord(recordInfo[0],recordInfo[1],recordInfo[2],recordInfo[3]));
                }
                
      

                patients.put(key, tempPatient);                
                
            }

        }
        catch(IOException e){
            System.out.println(e);
        }
        finally{
            try{
                if(bf!=null){
                    bf.close();
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }

    }

    /* =====================================================
                     GETTERS (Optional)
       ===================================================== */

    public Hashtable<Integer,Patient> getPatients() {
        return patients;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

}

