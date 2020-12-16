package pl.artsit.flexgoals.model.goal;

import java.util.Date;

public class QuantitativeGoal {

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

    public QuantitativeGoal(
            Integer id, String name, String description, Integer points,
            Boolean is_shared, Integer id_user, Integer days, String goal,
            String progress, Integer target, Integer step, Date date
    ) {
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

    public Boolean getShared() {
        return is_shared;
    }

    public void setShared(Boolean shared) {
        is_shared = shared;
    }

    public Integer getIdUser() {
        return id_user;
    }

    public void setIdUser(Integer idUser) {
        this.id_user = idUser;
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

    @Override
    public String toString() {
        return "quantitative_qoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", points=" + points +
                ", is_shared=" + is_shared +
                ", id_user=" + id_user +
                ", days=" + days +
                ", goal='" + goal + '\'' +
                ", progress='" + progress + '\'' +
                ", target=" + target +
                ", step=" + step +
                ", date=" + date +
                '}';
    }
}
