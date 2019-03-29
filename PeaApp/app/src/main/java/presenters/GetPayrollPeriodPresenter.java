package presenters;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import Model.PayrollPeriodGet;
import service.AppRepository;
import service.AppRepositoryImpl;
import service.CallBackData;
import views.PayrollPeriod;

public class GetPayrollPeriodPresenter {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private PayrollPeriod payrollPeriod;

    public GetPayrollPeriodPresenter(Context context, Activity activity, PayrollPeriod payrollPeriod) {
        this.context = context;
        this.activity = activity;
        this.payrollPeriod = payrollPeriod;
        this.appRepository = new AppRepositoryImpl();
    }
    public  void getPayrollPeriod(String token,int empId){
        appRepository.getPayrollPeriod(context, token, empId, new CallBackData<List<PayrollPeriodGet>>() {
            @Override
            public void onSuccess(List<PayrollPeriodGet> payrollPeriodGet) {
                payrollPeriod.getSuccess(payrollPeriodGet);
            }
            @Override
            public void onFail(String message) {
                payrollPeriod.getFail(message);
            }
        });
    }
}
