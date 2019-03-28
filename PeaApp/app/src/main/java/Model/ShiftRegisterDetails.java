package Model;

public class ShiftRegisterDetails {

    public int shiftId;
    public int timeFrameId;
    public String empName;
    public String status;
    public String startDate;
    public String endDate;
    public String startTime;
    public String endTime;
    public String timeFrameName;

    public String getTimeFrameName() {
        return timeFrameName;
    }

    public void setTimeFrameName(String timeFrameName) {
        this.timeFrameName = timeFrameName;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public int getTimeFrameId() {
        return timeFrameId;
    }

    public void setTimeFrameId(int timeFrameId) {
        this.timeFrameId = timeFrameId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
