public class Appointment {
    private int appointmentID;     // stores the appointment id
    private int patientID;         // stores the patient id
    private String doctor;         // stores the doctor name
    private String date;           // stores the appointment date
    private String time;           // stores the appointment time

    public Appointment(int appointmentID, int patientID, String doctor,
                       String date, String time) {
        this.appointmentID = appointmentID;   // sets the appointment id
        this.patientID = patientID;           // sets the patient id
        this.doctor = doctor;                 // sets the doctor name
        this.date = date;                     // sets the date
        this.time = time;                     // sets the time
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentID
                + ", Patient ID: " + patientID
                + ", Doctor: " + doctor
                + ", Date: " + date
                + ", Time: " + time;
    }
}
