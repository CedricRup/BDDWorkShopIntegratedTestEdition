package com.santa.restclient;

import com.santa.Enfant;
import com.santa.steps.ActionEnfant;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.time.OffsetDateTime;
import java.util.List;

public interface IRestClient {

    @GET("time")
    Call<OffsetDateTime> whatTimeisIt();

    @POST("enfants")
    Call<Void> enregistrerEnfant(@Body Enfant enfant);

    @POST("enfants/{prenom}/actions")
    Call<Void> enregistrerActions(@Path("prenom") String prenom, @Body List<ActionEnfant> actionsEnfant);

    @GET("enfants/{prenom}/sagesse/{annee}")
    Call<Integer> recupererPointDeSagesse(@Path("prenom")String prenom, @Path("annee") int annee);
}