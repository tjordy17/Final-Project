public class Doctor extends User {

    public Doctor(int userId, String name) {
        super(userId, name, "Doctor");
    }
      
        /*
        //Permissions to be added here in the future
        public boolean canEditMedicalRecords() {
        return true;
        }

        public boolean canGenerateReports() {
        return false;
         } */
}