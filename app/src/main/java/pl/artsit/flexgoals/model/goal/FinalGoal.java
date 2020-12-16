package pl.artsit.flexgoals.model.goal;

import java.util.Date;

public class FinalGoal {
    private Integer id;
    private String name;
    private String description;
    private String goal;
    private Integer days;
    private Integer points;
    private Boolean is_shared;
    private String progress;
    private Integer id_user;
    private Date date;

    public FinalGoal(Integer id, String name, String description, String goal, Integer days, Integer points, Boolean is_shared, String progress, Integer id_user, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
        this.points = points;
        this.is_shared = is_shared;
        this.progress = progress;
        this.id_user = id_user;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getShared() {
        return is_shared;
    }

    public void setShared(Boolean shared) {
        is_shared = shared;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getIdUser() {
        return id_user;
    }

    public void setIdUser(Integer idUser) {
        this.id_user = idUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "finalGoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", days=" + days +
                ", points=" + points +
                ", is_shared=" + is_shared +
                ", progress='" + progress + '\'' +
                ", id_user=" + id_user +
                ", date=" + date +
                '}';
    }
}
