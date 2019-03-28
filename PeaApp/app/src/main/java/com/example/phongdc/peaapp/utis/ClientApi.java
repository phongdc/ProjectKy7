package com.example.phongdc.peaapp.utis;


import com.example.phongdc.peaapp.service.AppService;


public class ClientApi extends BaseApi {
    public AppService appService() {
        return this.getService(AppService.class, ConfigApi.BASE_URL);
    }
}
