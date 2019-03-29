package Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class PayrollPeriodGet implements Serializable{
    @SerializedName("id")
    private  int id;
    @SerializedName("payslip_template_id")
    private int payslipTemplateId;
    @SerializedName("name")
    private String name;
    @SerializedName("from_date")
    private String fromDate;
    @SerializedName("to_date")
    private String toDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getPayslipTemplateId() {
        return payslipTemplateId;
    }

    public void setPayslipTemplateId(int payslipTemplateId) {
        this.payslipTemplateId = payslipTemplateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
