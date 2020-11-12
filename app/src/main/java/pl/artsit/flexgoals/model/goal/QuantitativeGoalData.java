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

    public QuantitativeGoalData(Integer userId, String name, String description, String goal, Integer days, Integer step) {
        this.id_user = userId;
        this.is_shared = false;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
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

    @Override
    public String toString() {
        return "QuantitativeGoalData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", is_shared=" + is_shared +
                ", id_user=" + id_user +
                ", days=" + days +
                ", goal='" + goal + '\'' +
                ", step=" + step +
                '}';
    }
}
