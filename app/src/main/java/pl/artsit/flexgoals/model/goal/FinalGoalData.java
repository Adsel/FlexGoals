package pl.artsit.flexgoals.model.goal;

public class FinalGoalData {
    private Integer id_user;
    private Boolean is_shared;
    private String name;
    private String description;
    private String goal;
    private Integer days;

    public FinalGoalData() { }

    public FinalGoalData(Integer userId, String name, String description, String goal, Integer days) {
        this.id_user = userId;
        this.is_shared = false;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
    }

    public Integer getUserId() {
        return this.id_user;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getGoal() {
        return this.goal;
    }

    public Integer getDays() {
        return this.days;
    }

    @Override
    public String toString() {
        return "FinalGoalData{" +
                "id_user=" + id_user +
                ", is_shared=" + is_shared +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", days=" + days +
                '}';
    }
}
