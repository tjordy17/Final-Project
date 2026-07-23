import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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

    public boolean registerPatient(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (patients.containsKey(patient.getPatientID())) {
            return false;
        }

        patients.put(patient.getPatientID(), patient);
        return true;
    }

    public Patient searchPatientByID(int patientId) {
        return patients.get(patientId);
    }

    public ArrayList<Patient> searchPatientByName(String name) {
        ArrayList<Patient> matches = new ArrayList<>();

        if (name == null || name.trim().isEmpty()) {
            return matches;
        }

        String target = name.trim().toLowerCase();

        for (Patient patient : patients.values()) {
            String firstName = patient.getFirstName();
            String lastName = patient.getLastName();

            if (firstName != null && firstName.toLowerCase().contains(target)
                    || lastName != null && lastName.toLowerCase().contains(target)) {
                matches.add(patient);
            }
        }

        return matches;
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
        if (patients.containsKey(patientId)) {
            patients.remove(patientId);
            return true;
        }

        return false;
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

    public boolean addMedicalRecord(MedicalRecord record) {
        if (record == null || record.getPatientID() <= 0) {
            return false;
        }

        Patient patient = patients.get(record.getPatientID());
        if (patient == null) {
            return false;
        }

        patient.addToRecord(record);
        return true;
    }

    public ArrayList<MedicalRecord> getMedicalRecords(int patientId) {
        Patient patient = patients.get(patientId);

        if (patient == null) {
            return new ArrayList<>();
        }

        return patient.getMedicalRecords();
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
        try (BufferedWriter patientWriter = new BufferedWriter(new FileWriter("patients.txt"))) {
            for (Patient patient : patients.values()) {
                patientWriter.write(patient.getPatientID() + "|"
                        + patient.getFirstName() + "|"
                        + patient.getLastName() + "|"
                        + patient.getAge() + "|"
                        + patient.getGender() + "|"
                        + patient.getPhone());
                patientWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
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

        try (BufferedWriter recordWriter = new BufferedWriter(new FileWriter("records.txt"))) {
            for (Patient patient : patients.values()) {
                for (MedicalRecord record : patient.getMedicalRecords()) {
                    recordWriter.write(patient.getPatientID() + "|"
                            + record.getDiagnosis() + "|"
                            + record.getTreatment() + "|"
                            + record.getMedication() + "|"
                            + record.getNotes());
                    recordWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving records: " + e.getMessage());
        }
    }

    public void loadData() {
        patients.clear();
        appointments.clear();

        try (BufferedReader patientReader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            while ((line = patientReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|", -1);
                if (fields.length < 6) {
                    continue;
                }

                int patientId = Integer.parseInt(fields[0]);
                Patient patient = new Patient(patientId, fields[1], fields[2], "", Integer.parseInt(fields[3]), fields[4], fields[5]);
                patients.put(patientId, patient);
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
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

        try (BufferedReader recordReader = new BufferedReader(new FileReader("records.txt"))) {
            String line;
            while ((line = recordReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|", -1);
                if (fields.length < 5) {
                    continue;
                }

                int patientId = Integer.parseInt(fields[0]);
                Patient patient = patients.get(patientId);
                if (patient != null) {
                    MedicalRecord record = new MedicalRecord(patientId, fields[1], fields[2], fields[3], fields[4]);
                    patient.addToRecord(record);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading records: " + e.getMessage());
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