package Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attendance implements Serializable{
    @SerializedName("check_min")
    private String checkMin;
    @SerializedName("check_max")
    private String checkMax;
    @SerializedName("total_hour")
    private String totalHour;
    @SerializedName("status")
    private  int status;
    @SerializedName("shift_min")
    private String shiftMin;
    @SerializedName("shift_max")
    private String shiftMax;
    public String getCheckMin() {
        return checkMin;
    }
    public void setCheckMin(String checkMin) {
        this.checkMin = checkMin;
    }

    public String getCheckMax() {
        return checkMax;
    }

    public void setCheckMax(String checkMax) {
        this.checkMax = checkMax;
    }

    public String getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(String totalHour) {
        this.totalHour = totalHour;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShiftMin() {
        return shiftMin;
    }

    public void setShiftMin(String shiftMin) {
        this.shiftMin = shiftMin;
    }

    public String getShiftMax() {
        return shiftMax;
    }

    public void setShiftMax(String shiftMax) {
        this.shiftMax = shiftMax;
    }
}
