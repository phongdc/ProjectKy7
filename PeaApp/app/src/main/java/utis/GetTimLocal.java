package utis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTimLocal {
    public  static  String getTime(){
        Date thoiGian = new Date();
        SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
        String showTime = dinhDangThoiGian.format(thoiGian.getTime());
        return  showTime;
    }
}
