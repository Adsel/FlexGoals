package pl.artsit.flexgoals.model;

public class PredefinedFinalGoal {
    private Integer id;
    private String name;
    private String description;
    private String goal;
    private Integer days;

    public PredefinedFinalGoal(Integer id, String name, String description, String goal, Integer days) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
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

    @Override
    public String toString() {
        return "predefinedFinalGoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", days=" + days +
                '}';
    }
}
