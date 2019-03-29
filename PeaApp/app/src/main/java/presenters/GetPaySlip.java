package presenters;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import Model.PaySlip;
import Model.PaySlipGet;
import service.AppRepository;
import service.AppRepositoryImpl;
import service.CallBackData;
import views.PaySlipView;

public class GetPaySlip {
    private Context context;
    private Activity activity;
    private AppRepository appRepository;
    private PaySlipView mPaySlipView;

    public GetPaySlip(Context context, Activity activity, PaySlipView mPaySlipView) {
        this.context = context;
        this.activity = activity;
        this.mPaySlipView = mPaySlipView;
        this.appRepository = new AppRepositoryImpl();
    }
    public  void getPaySlip(String token,int periodId,int empId){
        appRepository.getPaySlip(context, token, periodId, empId, new CallBackData<List<PaySlipGet>>() {
            @Override
            public void onSuccess(List<PaySlipGet> paySlip) {
                mPaySlipView.getSuccess(paySlip);
            }

            @Override
            public void onFail(String message) {
                mPaySlipView.getFail(message);
            }
        });
    }
}
