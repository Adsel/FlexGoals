package pl.artsit.flexgoals.model.user;

public class AuthData {
    private String login;
    private String password;
    private int id;

    public AuthData(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public AuthData(){}

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{\"login\":\"" + this.login + "\","
                + "\"password\":\"" + this.password + "\"}";
    }
}
