package utis;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueUtils {
    public static String formatDecimalPrice(Float number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number) + "đ";
    }

    public static int formatDecimalInt(Float number) {
        return number.intValue();
    }

    public static String getTime() {
        Date time = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
        String showTime = dinhDangThoiGian.format(time.getTime());
        return showTime;
    }

    public static String formatPhoneString(String phone) {
        String formatedPhone = "0";
        int idx = phone.indexOf("+84");
        if (idx < 0) {
            formatedPhone = phone;
        } else {
            formatedPhone = formatedPhone.concat(phone.substring(idx + 3));
        }
        return formatedPhone.trim();
    }
}
