package Model;

public class PayrollPeriod {
    private int id;
    private int payslip_template_id;
    private String name;
    private String from_date;
    private String to_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayslip_template_id() {
        return payslip_template_id;
    }

    public void setPayslip_template_id(int payslip_template_id) {
        this.payslip_template_id = payslip_template_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }
}
