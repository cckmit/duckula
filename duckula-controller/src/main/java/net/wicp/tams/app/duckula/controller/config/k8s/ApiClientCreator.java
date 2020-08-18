package net.wicp.tams.app.duckula.controller.config.k8s;

import java.io.IOException;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.Config;
import net.wicp.tams.common.thread.threadlocal.ObjectCreator;

public class ApiClientCreator implements ObjectCreator<ApiClient> {
    
    @Override
    public ApiClient createObject() {
        ApiClient apiClient;
        try {
            apiClient = Config.defaultClient();
        } catch (IOException e) {
            throw new RuntimeException("create apiClient error", e);
        }
        return apiClient;
    }
    
}