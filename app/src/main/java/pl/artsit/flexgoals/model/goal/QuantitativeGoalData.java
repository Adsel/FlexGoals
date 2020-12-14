package pl.artsit.flexgoals.model.goal;

public class QuantitativeGoalData {
    private String name;
    private String description;
    private Boolean is_shared;
    private Integer id_user;
    private Integer days;
    private String goal;
    private Integer step;

    public QuantitativeGoalData() {}

    public QuantitativeGoalData(String name, String description, Integer id_user, Integer days, String goal, Integer step) {
        this.name = name;
        this.description = description;
        this.is_shared = false;
        this.id_user = id_user;
        this.days = days;
        this.goal = goal;
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIs_shared() {
        return is_shared;
    }

    public Integer getId_user() {
        return id_user;
    }

    public Integer getDays() {
        return days;
    }

    public String getGoal() {
        return goal;
    }

    public Integer getStep() {
        return step;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_shared(Boolean is_shared) {
        this.is_shared = is_shared;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
