public abstract class User {

    private int userId;
    private String name;
    private String role;

    public User(int userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setRole(String role) {
        this.role = role;
    }

    public void displayUserInfo() {
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Role    : " + role);
    }

    @Override
    public String toString() {
        return "User ID: " + userId +
               ", Name: " + name +
               ", Role: " + role;
    }
}