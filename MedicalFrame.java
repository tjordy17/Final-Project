import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public class MedicalFrame extends Frame implements ActionListener {

    private final MedicalSystem medicalSystem;

    private final TextField patientIdField = new TextField();
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField ageField = new TextField();
    private final TextField genderField = new TextField();
    private final TextField phoneField = new TextField();
    private final TextField appointmentIdField = new TextField();
    private final TextField doctorField = new TextField();
    private final TextField appointmentDateField = new TextField();
    private final TextField appointmentTimeField = new TextField();
    private final TextField diagnosisField = new TextField();
    private final TextField treatmentField = new TextField();
    private final TextField medicationField = new TextField();
    private final TextField notesField = new TextField();

    private final TextArea resultArea = new TextArea();

    public MedicalFrame(MedicalSystem medicalSystem) {
        super("Medical Records Management System");
        this.medicalSystem = medicalSystem;

        setLayout(new BorderLayout(10, 10));
        setSize(900, 800);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        Panel mainPanel = new Panel(new BorderLayout(7, 8));

        Panel inputPanel = new Panel(new GridLayout(3, 1, 10, 15));
        Panel patientPanel = new Panel(new GridLayout(3,4,10,10));
        Panel patientContainer = new Panel(new BorderLayout());

        patientContainer.add(new Label("PATIENT INFORMATION"), BorderLayout.NORTH);
        patientContainer.add(patientPanel, BorderLayout.CENTER);

        patientPanel.add(new Label("Patient ID:"));
        patientPanel.add(patientIdField);

        patientPanel.add(new Label("First Name:"));
        patientPanel.add(firstNameField);

        patientPanel.add(new Label("Last Name:"));
        patientPanel.add(lastNameField);

        patientPanel.add(new Label("Age:"));
        patientPanel.add(ageField);

        patientPanel.add(new Label("Gender:"));
        patientPanel.add(genderField);

        patientPanel.add(new Label("Phone:"));
        patientPanel.add(phoneField);
        
        Panel appointmentPanel = new Panel(new GridLayout(2,4,10,10));
        Panel appointmentContainer = new Panel(new BorderLayout());

    
        appointmentContainer.add(new Label("APPOINTMENT INFORMATION"), BorderLayout.NORTH);

        appointmentContainer.add(appointmentPanel, BorderLayout.CENTER);
        appointmentPanel.add(new Label("Appointment ID:"));
        appointmentPanel.add(appointmentIdField);
        appointmentPanel.add(new Label("Doctor:"));
        appointmentPanel.add(doctorField);
        appointmentPanel.add(new Label("Date:"));
        appointmentPanel.add(appointmentDateField);
        appointmentPanel.add(new Label("Time:"));
        appointmentPanel.add(appointmentTimeField);

        Panel recordPanel = new Panel(new GridLayout(4, 2, 10, 10));
        Panel recordContainer = new Panel(new BorderLayout());
        recordContainer.add(new Label("MEDICAL RECORD INFORMATION"), BorderLayout.NORTH);
        recordContainer.add(recordPanel, BorderLayout.CENTER);
        recordPanel.add(new Label("Diagnosis:"));
        recordPanel.add(diagnosisField);
        recordPanel.add(new Label("Treatment:"));
        recordPanel.add(treatmentField);
        recordPanel.add(new Label("Medication:"));
        recordPanel.add(medicationField);
        recordPanel.add(new Label("Notes:"));
        recordPanel.add(notesField);

        
        inputPanel.add(patientContainer);
        inputPanel.add(appointmentContainer);
        inputPanel.add(recordContainer);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        Button registerButton = new Button("Register Patient");
        Button searchButton = new Button("Search Patient");
        Button showAllButton = new Button("Show All Patients");
        Button scheduleButton = new Button("Schedule Appointment");
        Button addRecordButton = new Button("Add Record");
        Button loadButton = new Button("Load Data");
        Button saveButton = new Button("Save Data");
        // Add action listeners for buttons
        registerButton.addActionListener(this);
        searchButton.addActionListener(this);
        showAllButton.addActionListener(this);
        scheduleButton.addActionListener(this);
        addRecordButton.addActionListener(this);
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        // Add buttons to the button panel
        buttonPanel.add(registerButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);
        buttonPanel.add(scheduleButton);
        buttonPanel.add(addRecordButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);

        resultArea.setEditable(false);
        resultArea.setText("Medical Records Management System\n\nUse the grouped sections above to register patients, schedule appointments, and add records.\n");

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
 
        add(mainPanel, BorderLayout.CENTER);
        add(resultArea, BorderLayout.SOUTH);
        setVisible(true);
    }
    //this method creates a section panel with a title and specified number of rows and columns for the content.
    private Panel createSectionPanel(String title, int rows, int cols) {
        Panel sectionPanel = new Panel(new BorderLayout(5, 5));
        sectionPanel.setBackground(Color.WHITE);

        Label titleLabel = new Label(title);
        titleLabel.setAlignment(Label.CENTER);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        Panel contentPanel = new Panel(new GridLayout(rows, cols, 6, 6));
        contentPanel.setBackground(Color.WHITE);

        sectionPanel.add(titleLabel, BorderLayout.NORTH);
        sectionPanel.add(contentPanel, BorderLayout.CENTER);
        return contentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Register Patient":
                registerPatient();
                break;
            case "Search Patient":
                searchPatient();
                break;
            case "Show All Patients":
                showAllPatients();
                break;
            case "Schedule Appointment":
                scheduleAppointment();
                break;
            case "Show Patients":
                showPatients();
                break;
            case "Show Appointments":
                showAppointments();
                break;
            case "Load Data":
                medicalSystem.loadData();
                resultArea.append("\nData loaded from text files.\n");
                break;
            case "Save Data":
                medicalSystem.saveData();
                resultArea.append("\nData saved to text files.\n");
                break;
            default:
                resultArea.append("\nUnsupported action.\n");
        }
    }

    private void registerPatient() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText().trim());
            int age = Integer.parseInt(ageField.getText().trim());

            Patient patient = new Patient(patientId,
                    firstNameField.getText().trim(),
                    lastNameField.getText().trim(),
                    "",
                    age,
                    genderField.getText().trim(),
                    phoneField.getText().trim());

            boolean success = medicalSystem.registerPatient(patient);
            resultArea.append(success
                    ? "Patient registered successfully.\n"
                    : "Patient registration failed. Check for duplicate ID.\n");
        } catch (NumberFormatException ex) {
            resultArea.append("Please enter a valid numeric patient ID or age.\n");
        }
    }

    private void searchPatient() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText().trim());
            Patient patient = medicalSystem.searchPatientByID(patientId);

            if (patient == null) {
                resultArea.append("No patient found with ID " + patientId + ".\n");
            } else {
                resultArea.append(patient.toString() + "\n");
            }
        } catch (NumberFormatException ex) {
            resultArea.append("Please enter a valid numeric patient ID.\n");
        }
    }

    private void showAllPatients() {
        Hashtable<Integer, Patient> patients = medicalSystem.getPatients();

        if (patients.isEmpty()) {
            resultArea.append("No patients registered.\n");
            return;
        }

        resultArea.append("\n=== All Patients ===\n");
        for (Patient patient : patients.values()) {
            resultArea.append(patient.toString() + "\n");
        }
    }

    private void scheduleAppointment() {
        try {
            int appointmentId = Integer.parseInt(appointmentIdField.getText().trim());
            int patientId = Integer.parseInt(patientIdField.getText().trim());

            Appointment appointment = new Appointment(appointmentId,
                    patientId,
                    doctorField.getText().trim(),
                    appointmentDateField.getText().trim(),
                    appointmentTimeField.getText().trim());

            boolean success = medicalSystem.scheduleAppointment(appointment);
            resultArea.append(success
                    ? "Appointment scheduled successfully.\n"
                    : "Appointment scheduling failed. Check the patient ID or duplicate appointment ID.\n");
        } catch (NumberFormatException ex) {
            resultArea.append("Please enter valid numeric values for appointment ID and patient ID.\n");
        }
    }

    private void showPatients() {
        Hashtable<Integer, Patient> patients = medicalSystem.getPatients();

        if (patients.isEmpty()) {
            resultArea.append("No patients registered.\n");
            return;
        }

        resultArea.append("\n=== All Patients ===\n");
        for (Patient patient : patients.values()) {
            resultArea.append(patient.toString() + "\n");
        }
    }
    private void showAppointments() {
         ArrayList<Appointment> appointments = medicalSystem.getAppointments();

        if (appointments.isEmpty()) {
            resultArea.append("No appointments scheduled.\n");
            return;
        }

        resultArea.append("\n=== All Appointments ===\n");
        for (Appointment appointment : appointments) {
            resultArea.append(appointment.toString() + "\n");
        }
    }
}
