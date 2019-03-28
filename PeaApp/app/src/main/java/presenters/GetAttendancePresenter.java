package presenters;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import Model.Attendance;
import service.AppRepository;
import service.AppRepositoryImpl;
import service.CallBackData;
import views.AttendanceView;

public class GetAttendancePresenter {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private AttendanceView attendanceView;

    public GetAttendancePresenter(Context context, Activity activity, AttendanceView attendanceView) {
        this.context = context;
        this.activity = activity;
        this.attendanceView = attendanceView;
        this.appRepository = new AppRepositoryImpl();
    }
    public void getAttendace(String toDate,String fromDate,int empId){
        appRepository.getAttendence(context, toDate, fromDate, empId, new CallBackData<List<Attendance>>() {
            @Override
            public void onSuccess(List<Attendance> attendances) {
                attendanceView.getAttendance(attendances);
            }

            @Override
            public void onFail(String message) {
                attendanceView.getFail(message);
            }
        });
    }
}
