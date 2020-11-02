package pl.artsit.flexgoals.model;

public class userx {
    private Integer id;
    private String login;
    private String password;
    private Integer points;
    private String email;

    public userx(Integer id, String login, String password, Integer points, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.points = points;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", points=" + points +
                ", email='" + email + '\'' +
                '}';
    }
}
