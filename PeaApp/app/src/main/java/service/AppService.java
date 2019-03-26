package service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import utis.ConfigApi;


public interface AppService {
    @GET(ConfigApi.Api.ATTENDANCE)
    Call<ResponseBody> getAttendence();


}
