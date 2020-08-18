package net.wicp.tams.app.duckula.controller.config.k8s;

import io.kubernetes.client.openapi.ApiClient;
import net.wicp.tams.common.thread.threadlocal.PerthreadManager;

public abstract class ApiClientManager {
    
    public static ApiClient getApiClient() {
        ApiClient createObject = PerthreadManager.getInstance()
                .createValue("need-ApiClientManager", new ApiClientCreator()).createObject();
        return createObject;
    }
}
