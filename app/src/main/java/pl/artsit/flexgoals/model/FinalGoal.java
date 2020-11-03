package pl.artsit.flexgoals.model;

import java.util.Date;

public class FinalGoal {
    private Integer id;
    private String name;
    private String description;
    private String goal;
    private Integer days;
    private Integer points;
    private Boolean isShared;
    private String progress;
    private Integer idUser;
    private Date date;

    public FinalGoal(Integer id, String name, String description, String goal, Integer days, Integer points, Boolean isShared, String progress, Integer idUser, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
        this.points = points;
        this.isShared = isShared;
        this.progress = progress;
        this.idUser = idUser;
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
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
                ", isShared=" + isShared +
                ", progress='" + progress + '\'' +
                ", idUser=" + idUser +
                ", date=" + date +
                '}';
    }
}
