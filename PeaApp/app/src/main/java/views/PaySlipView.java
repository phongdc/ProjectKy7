package views;

import java.util.List;

import Model.PaySlip;
import Model.PaySlipGet;
public interface PaySlipView {
    void getSuccessPaySlip(List<PaySlipGet> mPaySlip);
    void getFail(String message);
}
