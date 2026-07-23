import java.util.ArrayList;

public class Patient {

    public int Id;

    private ArrayList<MedicalRecord> medicalRecords;

    private int age;

    private String phone_number;
    private String first_name;
    private String last_name;    
    private String gender;
    private String date_of_birth;

    public Patient(int id,String firstName,String lastName, String date_of_birth, int age,String gender,String phone){

        medicalRecords = new ArrayList<MedicalRecord>();

        this.Id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.date_of_birth = date_of_birth;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone;
    }

    public int getPatientID(){return Id;}

    public String getFirstName(){return first_name;}

    public String getLastName(){return last_name;}

    public int getAge(){return age;}

    public String getGender(){return gender;}

    public String getPhone(){return phone_number;}

    public ArrayList<MedicalRecord> getMedicalRecords(){
        return medicalRecords;
    }

    public void addToRecord(MedicalRecord record){
        medicalRecords.add(record);
    }

    public void setFirstName(String name){
        this.first_name = name;
    }

    public void setLastName(String name){
        this.last_name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setPhone(String phone){
        this.phone_number = phone;
    }

    private String format_medical_records_for_output(){
        String outputString = "{";
        for(MedicalRecord m : medicalRecords){
            outputString += m +",";
        }
        return outputString + "}";
    }

    public String toString(){
        return String.format("{%s,%s,%s,%s,%s,%s,%s}",first_name,last_name,date_of_birth,age,""+gender,phone_number,format_medical_records_for_output());
    }

}