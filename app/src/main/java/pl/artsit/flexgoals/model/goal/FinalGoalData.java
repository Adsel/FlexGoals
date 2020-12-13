package pl.artsit.flexgoals.model.goal;

public class FinalGoalData {
    private Integer id_user;
    private Boolean is_shared;
    private String name;
    private String description;
    private String goal;
    private Integer days;

    FinalGoalData() {}

    public FinalGoalData(Integer id_user, String name, String description, String goal, Integer days) {
        this.id_user = id_user;
        this.is_shared = false;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
    }

    public Integer getIdUser() {
        return id_user;
    }

    public Boolean getIsShared() {
        return is_shared;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getGoal() {
        return goal;
    }

    public Integer getDays() {
        return days;
    }

    public void setIdUser(Integer id_user) {
        this.id_user = id_user;
    }

    public void setIsShared(Boolean is_shared) {
        this.is_shared = is_shared;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
