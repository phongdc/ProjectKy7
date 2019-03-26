package utis;


import service.AppService;

public class ClientApi extends BaseApi {
    public AppService appService() {
        return this.getService(AppService.class, ConfigApi.BASE_URL);
    }
}
