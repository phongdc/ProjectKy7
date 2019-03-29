package views;

import java.util.List;

import Model.PayrollPeriodGet;

public interface PayrollPeriod {
    void getSuccess(List<PayrollPeriodGet> mPayrollPeriodGetList);
    void getFail(String message);
}
