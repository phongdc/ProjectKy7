package com.example.phongdc.peaapp.service;

import com.example.phongdc.peaapp.utis.ConfigApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AppService {
    @GET(ConfigApi.Api.ATTENDANCE)
    Call<ResponseBody> getAttendence(@Query("to_date") String toDate, @Query("from_date") String fromDate, @Query("emp_id") int empId);

    @GET(ConfigApi.Api.STATUS)
    Call<ResponseBody> getAttendenceStatus(@Query("to_date") String toDate, @Query("from_date") String fromDate, @Query("emp_id") int empId);
}
