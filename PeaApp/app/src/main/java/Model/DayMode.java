package Model;

import org.json.JSONArray;

public class DayMode {
    private int id;
    private boolean active;
    private String name;
    private String priority;
    private String nameDay;
    private JSONArray dayOfWeek;

    public JSONArray getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(JSONArray dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNameDay() {
        return nameDay;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }
}
