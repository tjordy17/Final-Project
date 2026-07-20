public abstract class User {

    protected int userID;

    protected String name;

    protected String role;

    public User(int id,String name);

    public int getUserID();

    public String getName();

    public String getRole();

    public abstract void displayMenu();

}