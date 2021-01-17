package pl.artsit.flexgoals.model.user;

public class User {
    private Integer id;
    private String password;
    private String login;
    private Integer points;
    private String email;

    public User(Integer id, String password, String login, Integer points, String email) {
        this.id = id;
        this.password = password;
        this.login = login;
        this.points = points;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
