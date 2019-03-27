package Model;

public class DayType {
    private int id;
    private String name;
    private String days_of_the_week;
    private String from_date;
    private String to_date;
    private String priority;
    private String date_create;

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

    public String getDays_of_the_week() {
        return days_of_the_week;
    }

    public void setDays_of_the_week(String days_of_the_week) {
        this.days_of_the_week = days_of_the_week;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }



    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }
}
