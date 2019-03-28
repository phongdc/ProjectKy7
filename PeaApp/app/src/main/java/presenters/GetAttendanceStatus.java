package presenters;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import Model.Status;
import service.AppRepository;
import service.AppRepositoryImpl;
import service.CallBackData;
import views.AttendaneStatus;

public class GetAttendanceStatus {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private AttendaneStatus attendaneStatus;

    public GetAttendanceStatus(Context context, Activity activity, AttendaneStatus attendaneStatus) {
        this.context = context;
        this.activity = activity;
        this.attendaneStatus = attendaneStatus;
        this.appRepository = new AppRepositoryImpl();
    }
    public void getAttendanceStatus(String toDate,String fromDate,int empId){
        appRepository.getAttendenceStatus(context, toDate, fromDate, empId, new CallBackData<List<Status>>() {
            @Override
            public void onSuccess(List<Status> statuses) {
                attendaneStatus.getAttdanceStatus(statuses);
            }

            @Override
            public void onFail(String message) {
            attendaneStatus.getFail(message);
            }
        });
    }
}
