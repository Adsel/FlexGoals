package pl.artsit.flexgoals.model.goal.quantitative;

public class QuantitativeGoalProgress {
    private Integer id;
    private Integer val;

    public QuantitativeGoalProgress(Integer id, Integer val) {
        this.id = id;
        this.val = val;
    }

    public QuantitativeGoalProgress() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
