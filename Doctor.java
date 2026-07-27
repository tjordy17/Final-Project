public class Doctor extends User {

    public Doctor(int userId, String name) {
        super(userId, name, "Doctor");
    }
      
  //this subclass represents a doctor user in the system, inheriting from the User class.
  // It has a constructor that initializes the doctor with a user ID and name, and sets the role to "Doctor".
}