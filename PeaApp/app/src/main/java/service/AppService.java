package service;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import utis.ConfigApi;


public interface AppService {
    @GET(ConfigApi.Api.ATTENDANCE)
    Call<ResponseBody> getAttendence(
           @Query("to_date") String toDate,
           @Query("from_date") String fromDate,
           @Query("emp_id") int empId
    );

    @GET(ConfigApi.Api.STATUS)
    Call<ResponseBody> getAttendenceStatus(
            @Query("to_date") String toDate,
            @Query("from_date") String fromDate,
            @Query("emp_id") int empId
    );
    @GET(ConfigApi.Api.PAYROLL_PERIOD)
    Call<ResponseBody> getPayROll_PERIOD( @HeaderMap Map<String, String> header,
            @Query("emp_id") int  empID
    );
    @GET(ConfigApi.Api.PAYSLIP)
    Call<ResponseBody> getPaySlip( @HeaderMap Map<String, String> header,
                                          @Query("period_id") int periodId,
                                          @Query("emp_id") int  empID
    );
}
