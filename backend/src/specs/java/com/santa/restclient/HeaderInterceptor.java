package com.santa.restclient;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HeaderInterceptor implements Interceptor {

    private String token;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (token != null) {
            String bearer = token;
            request = request.newBuilder()
                    .addHeader("Authorization", "Bearer " + bearer)
                    .build();
        }
        Response response = chain.proceed(request);
        return response;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}