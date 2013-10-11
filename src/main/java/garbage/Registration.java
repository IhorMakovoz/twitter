package garbage;

public class Registration {


    private String login;
    private String password;

    public Registration(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Registration(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return String.format("{ %s }", login);
    }
}
