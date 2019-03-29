package com.example.phongdc.peaapp.service;

import com.example.phongdc.peaapp.utis.ConfigApi;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;


public interface AppService {
    @GET(ConfigApi.Api.ATTENDANCE)
    Call<ResponseBody> getAttendence(@HeaderMap Map<String, String> header,@Query("to_date") String toDate, @Query("from_date") String fromDate, @Query("emp_id") int empId);

    @GET(ConfigApi.Api.STATUS)
    Call<ResponseBody> getAttendenceStatus(@HeaderMap Map<String, String> header,@Query("to_date") String toDate, @Query("from_date") String fromDate, @Query("emp_id") int empId);
    @GET(ConfigApi.Api.PAYROLL_PERIOD)
    Call<ResponseBody> getPayROll_PERIOD(@HeaderMap Map<String, String> header, @Query("emp_id") int empID);
    @GET(ConfigApi.Api.PAYSLIP)
    Call<ResponseBody> getPaySlip(@HeaderMap Map<String, String> header, @Query("period_id") int periodId, @Query("emp_id") int empID);
}
