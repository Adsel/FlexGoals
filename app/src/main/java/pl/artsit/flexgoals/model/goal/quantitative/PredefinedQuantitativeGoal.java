package pl.artsit.flexgoals.model.goal.quantitative;

public class PredefinedQuantitativeGoal {
    private Integer id;
    private String name;
    private String description;
    private String goal;
    private Integer days;
    private Integer target;
    private Integer step;

    public PredefinedQuantitativeGoal() {

    }

    public PredefinedQuantitativeGoal(Integer id, String name, String description, String goal, Integer days, Integer target, Integer step) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.days = days;
        this.target = target;
        this.step = step;
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

    @Override
    public String toString() {
        return "predefinedQuantitativeGoal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", goal='" + goal + '\'' +
                ", days=" + days +
                ", target=" + target +
                ", step=" + step +
                '}';
    }
}
