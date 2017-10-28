package com.santa.restclient;

import retrofit2.Call;
import retrofit2.http.GET;

import java.time.OffsetDateTime;

public interface IRestClient {
    //@POST("somewhere")
    //Call<Void> something(@Path("here") String here, @Body someObject);

    @GET("time")
    Call<OffsetDateTime> whatTimeisIt();

}