// Handles user login

public abstract class User {
    protected String email;
    protected String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean login(String inputEmail, String inputPassword) {
        return this.email.equals(inputEmail) && this.password.equals(inputPassword);
    }
}