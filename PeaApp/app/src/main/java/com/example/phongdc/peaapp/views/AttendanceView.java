package com.example.phongdc.peaapp.views;

import java.util.List;

import Model.Attendance;

public interface AttendanceView {
    void getAttendance(List<Attendance> attendanceList);
    void getFail(String message);
}
