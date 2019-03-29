package utis;

public class ConfigApi {
    public static final String BASE_URL = "http://payroll.unicode.edu.vn/api/";

    public interface Api {
        String ATTENDANCE = "attendance";
        String STATUS = "attendance/status";
        String PAYROLL_PERIOD= "payroll_period";
        String PAYSLIP = "payslip";
    }
}
