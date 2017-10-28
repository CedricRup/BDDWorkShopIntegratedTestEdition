package com.santa.utils;

import com.santa.restclient.IRestClient;
import com.santa.restclient.HeaderInterceptor;

public class User {
    private IRestClient restClient;
    HeaderInterceptor headerInterceptor;

    public User(IRestClient restClient, HeaderInterceptor headerInterceptor) {
        this.restClient = restClient;
        this.headerInterceptor = headerInterceptor;
    }

    public IRestClient getRestClient() {
        return restClient;
    }

}
