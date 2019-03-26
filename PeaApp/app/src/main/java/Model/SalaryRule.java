package Model;

public class SalaryRule {
    private int id;
    private String name;
    private int timemode_id;
    private int duration_min;
    private int duration_max;
    private int value;
    private double rate_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimemode_id() {
        return timemode_id;
    }

    public void setTimemode_id(int timemode_id) {
        this.timemode_id = timemode_id;
    }

    public int getDuration_min() {
        return duration_min;
    }

    public void setDuration_min(int duration_min) {
        this.duration_min = duration_min;
    }

    public int getDuration_max() {
        return duration_max;
    }

    public void setDuration_max(int duration_max) {
        this.duration_max = duration_max;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getRate_value() {
        return rate_value;
    }

    public void setRate_value(double rate_value) {
        this.rate_value = rate_value;
    }
}
