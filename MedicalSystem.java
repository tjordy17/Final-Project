import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Painter;

public class MedicalSystem {

    //we set up integerTracker to keep track of the next available ID for patients and appointments
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
        if (updatedPatient == null || !patients.containsKey(patientId)) {
            return false;
        }

        Patient existingPatient = patients.get(patientId);
        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setAge(updatedPatient.getAge());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setPhone(updatedPatient.getPhone());

        return true;
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
        if (appointment == null) {
            return false;
        }

        if (appointment.getPatientID() <= 0 || !patients.containsKey(appointment.getPatientID())) {
            return false;
        }

        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getAppointmentID() == appointment.getAppointmentID()) {
                return false;
            }
        }

        appointments.add(appointment);
        return true;
    }

    public boolean updateAppointment(int appointmentId,
                                     Appointment updatedAppointment) {
        if (updatedAppointment == null) {
            return false;
        }

        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentId) {
                appointment.setDoctor(updatedAppointment.getDoctor());
                appointment.setDate(updatedAppointment.getDate());
                appointment.setTime(updatedAppointment.getTime());
                return true;
            }
        }

        return false;
    }

    public boolean cancelAppointment(int appointmentId) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentID() == appointmentId) {
                appointments.remove(i);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Appointment> getAppointments(int patientId) {
        ArrayList<Appointment> patientAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatientID() == patientId) {
                patientAppointments.add(appointment);
            }
        }

        return patientAppointments;
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
        if (updatedRecord == null) {
            return false;
        }

        Patient patient = patients.get(patientId);
        if (patient == null || patient.getMedicalRecords().isEmpty()) {
            return false;
        }

        ArrayList<MedicalRecord> records = patient.getMedicalRecords();
        records.set(records.size() - 1, updatedRecord);
        return true;
    }

    /* =====================================================
                        REPORTS
       ===================================================== */

    public void generatePatientReport() {
        System.out.println("\n=== Patient Report ===");

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient patient : patients.values()) {
            System.out.println("Patient ID: " + patient.getPatientID()
                    + ", Name: " + patient.getFirstName() + " " + patient.getLastName()
                    + ", Age: " + patient.getAge()
                    + ", Gender: " + patient.getGender()
                    + ", Phone: " + patient.getPhone());
        }
    }

    public void generateAppointmentReport() {
        System.out.println("\n=== Appointment Report ===");

        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }

        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public void generateMedicalHistoryReport() {
        System.out.println("\n=== Medical History Report ===");

        boolean hasRecords = false;

        for (Patient patient : patients.values()) {
            if (patient.getMedicalRecords().isEmpty()) {
                continue;
            }

            hasRecords = true;
            System.out.println("Patient: " + patient.getFirstName() + " " + patient.getLastName()
                    + " (ID: " + patient.getPatientID() + ")");

            for (MedicalRecord record : patient.getMedicalRecords()) {
                System.out.println("  Diagnosis: " + record.getDiagnosis()
                        + ", Treatment: " + record.getTreatment()
                        + ", Medication: " + record.getMedication()
                        + ", Notes: " + record.getNotes());
            }
        }

        if (!hasRecords) {
            System.out.println("No medical records available.");
        }
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

        
        try (BufferedWriter appointmentWriter = new BufferedWriter(new FileWriter("appointments.txt"))) {
            for (Appointment appointment : appointments) {
                appointmentWriter.write(appointment.getAppointmentID() + "|"
                        + appointment.getPatientID() + "|"
                        + appointment.getDoctor() + "|"
                        + appointment.getDate() + "|"
                        + appointment.getTime());
                appointmentWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }

    }

    public void loadData() {
        patients.clear();
        appointments.clear();
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

        try (BufferedReader appointmentReader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = appointmentReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|", -1);
                if (fields.length < 5) {
                    continue;
                }

                Appointment appointment = new Appointment(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3], fields[4]);
                appointments.add(appointment);
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
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

