public class Admin implements User{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) throws InvalidLoginException {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Admin login successful.");
            return true;
        } else {
            throw new InvalidLoginException("Invalid Admin credentials!");
        }
    }

    @Override
    public void register(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Admin registered successfully.");
    }

}
