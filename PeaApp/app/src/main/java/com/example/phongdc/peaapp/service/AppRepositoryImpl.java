package com.example.phongdc.peaapp.service;


import android.content.Context;

import com.example.phongdc.peaapp.utis.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Attendance;
import Model.PaySlipGet;
import Model.PayrollPeriodGet;
import Model.ResponseResult;
import Model.Status;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository {
    @Override
    public void getAttendence(Context context,String token,String to_date, String from_date, int emp_id, final CallBackData<List<Attendance>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> serviceCall = clientApi.appService().getAttendence(map,to_date,from_date,emp_id);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<List<Attendance>>>() {
                        }.getType();
                        ResponseResult<List<Attendance>> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        } else {
                            List<Attendance> attendances = responseResult.getData();
                            callBackData.onSuccess(attendances);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("No Data");
            }
        });
    }

    @Override
    public void getAttendenceStatus(Context context,String token,String to_date, String from_date, int emp_id, final CallBackData<List<Status>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> serviceCall = clientApi.appService().getAttendenceStatus(map,to_date,from_date,emp_id);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<List<Status>>>() {
                        }.getType();
                        ResponseResult<List<Status>> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        } else {
                            List<Status> statuses = responseResult.getData();
                            callBackData.onSuccess(statuses);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("No Data");
            }
        });
    }

    @Override
    public void getPayrollPeriod(Context context, String token, int empId,  final CallBackData<List<PayrollPeriodGet>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> serviceCall = clientApi.appService().getPayROll_PERIOD(map,empId);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<List<PayrollPeriodGet>>>() {
                        }.getType();
                        ResponseResult<List<PayrollPeriodGet>> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        } else {
                           List<PayrollPeriodGet> data = responseResult.getData();
                            callBackData.onSuccess(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callBackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getPaySlip(Context context, String token, int periodId, int empId,  final CallBackData<List<PaySlipGet>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String hearder = token;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", hearder);
        Call<ResponseBody> serviceCall = clientApi.appService().getPaySlip(map,periodId,empId);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 && response.body() != null) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ResponseResult<List<PaySlipGet>>>() {
                        }.getType();
                        ResponseResult<List<PaySlipGet>> responseResult = new Gson().fromJson(result, type);
                        if (responseResult.getData() == null) {
                            callBackData.onFail(response.message());
                        } else {
                            List<PaySlipGet> data = responseResult.getData();
                            callBackData.onSuccess(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    callBackData.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callBackData.onFail(t.getMessage());
            }
        });
    }
}
