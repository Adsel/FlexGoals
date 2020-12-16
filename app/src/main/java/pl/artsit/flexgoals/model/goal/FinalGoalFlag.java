package pl.artsit.flexgoals.model.goal;

import java.util.Date;

public class FinalGoalFlag {
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
    private Integer flag;

    public FinalGoalFlag() {
    }

    public FinalGoalFlag(Integer id, String name, String description, String goal, Integer days, Integer points, Boolean is_shared, String progress, Integer id_user, Date date, Integer flag) {
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
        this.flag = flag;
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

    public Boolean getIs_shared() {
        return is_shared;
    }

    public void setIs_shared(Boolean is_shared) {
        this.is_shared = is_shared;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}