package pl.artsit.flexgoals.model.goal;

import java.util.Date;

public class QuantitativeGoalFlag {
    private Integer id;
    private String name;
    private String description;
    private Integer points;
    private Boolean is_shared;
    private Integer id_user;
    private Integer days;
    private String goal;
    private String progress;
    private Integer target;
    private Integer step;
    private Date date;
    private Integer flag;

    public QuantitativeGoalFlag() {
    }

    public QuantitativeGoalFlag(Integer id, String name, String description, Integer points, Boolean is_shared, Integer id_user, Integer days, String goal, String progress, Integer target, Integer step, Date date, Integer flag) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.points = points;
        this.is_shared = is_shared;
        this.id_user = id_user;
        this.days = days;
        this.goal = goal;
        this.progress = progress;
        this.target = target;
        this.step = step;
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

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
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

