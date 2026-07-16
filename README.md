# CLI-Based Medical Records System

## Project Overview

The **CLI-Based Medical Records System** is a Java console application designed to help healthcare staff manage patient information, appointments, and medical records. The system allows users to register patients, schedule appointments, maintain medical histories, search records efficiently, and generate reports.

This project demonstrates the use of Java object-oriented programming concepts including classes, inheritance, polymorphism, collections, generics, exception handling, file I/O, and multithreading.

---

## Features

### Patient Management
- Register new patients
- Update patient information
- Search patients by ID or name
- View patient details

### Appointment Management
- Schedule appointments
- Update appointments
- Cancel appointments
- View appointment schedule

### Medical Record Management
- Add medical records
- Update medical history
- View patient medical records

### Search Functionality
- Search patients by:
  - Patient ID
  - Name

### Report Generation
- Patient demographics
- Appointment history
- Medical history reports
- Export reports to text files

### Data Persistence
- Save patient data
- Save appointments
- Save medical records
- Load existing data when the program starts

---

# Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Collections (ArrayList)
- Generics
- File I/O
- Exception Handling
- Multithreading
- Command Line Interface (CLI)

---

# Project Structure

```
MedicalRecordsSystem/
│
├── Main.java
├── MedicalInterface.java
├── MedicalSystem.java
│
├── Patient.java
├── MedicalRecord.java
├── Appointment.java
│
├── User.java
├── Doctor.java
├── Nurse.java
├── Admin.java
│
├── patients.txt
├── appointments.txt
├── records.txt
│
└── README.md
```

---

# Class Responsibilities

| Class | Description |
|---------|-------------|
| Main | Starts the application |
| MedicalInterface | Handles all menu interactions |
| MedicalSystem | Manages all system operations |
| Patient | Stores patient information |
| MedicalRecord | Stores diagnosis and treatment history |
| Appointment | Stores appointment details |
| User | Base class for all system users |
| Doctor | Doctor user type |
| Nurse | Nurse user type |
| Admin | Administrator user type |

---

# Team Responsibilities

## Team Member 1
### Patient & Medical Records

Responsible for:
- Patient.java
- MedicalRecord.java
- Patient registration
- Medical record management
- Patient searching
- File handling for patient records

---

## Team Member 2
### Appointments & Reports

Responsible for:
- Appointment.java
- Appointment scheduling
- Appointment updates
- Appointment cancellation
- Report generation
- Appointment file handling

---

## Team Member 3
### System Integration

Responsible for:
- Main.java
- MedicalSystem.java
- MedicalInterface.java
- User.java
- Doctor.java
- Nurse.java
- Admin.java
- Exception handling
- Collections
- Inheritance
- Polymorphism
- Multithreading
- Final testing and integration

---

# Object-Oriented Concepts Used

## Encapsulation
Private fields with getters and setters.

## Inheritance
```
User
 ├── Doctor
 ├── Nurse
 └── Admin
```

## Polymorphism
Different user types can override methods such as:

```java
displayMenu()
```

or

```java
performRole()
```

---

# Data Storage

The application stores information using text files.

Example files:

```
patients.txt
appointments.txt
records.txt
```

---

# How to Run

## Compile

```bash
javac *.java
```

## Run

```bash
java Main
```

---

# Example Menu

```
==============================
 Medical Records System
==============================

1. Register Patient
2. Search Patient
3. Add Medical Record
4. View Medical Record
5. Schedule Appointment
6. Update Appointment
7. Cancel Appointment
8. Generate Report
9. Save Data
10. Exit

Choose an option:
```

---

# Future Improvements

- GUI version using JavaFX or Swing
- Database integration (MySQL)
- User authentication
- Password encryption
- Email/SMS appointment reminders
- PDF report generation

---

# Testing

The following functionalities should be tested:

- Register patient
- Search patient
- Update patient information
- Add medical record
- Schedule appointment
- Cancel appointment
- Generate report
- Save data
- Load data
- Exception handling

---

# Authors

- Krish Patel – Patient & Medical Records
- Fariha Haq – Appointment Management & Reports
- Jordy Ndibanje – System Integration & User Interface

---

# License

This project was developed for educational purposes as part of a Java programming course.