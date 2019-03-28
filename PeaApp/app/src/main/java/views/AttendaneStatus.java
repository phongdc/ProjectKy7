package views;


import java.util.List;

import Model.Status;

public interface AttendaneStatus {
    void getAttdanceStatus(List<Status> statusList);
    void getFail(String message);
}
