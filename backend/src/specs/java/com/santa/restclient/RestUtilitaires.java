package com.santa.restclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santa.utils.User;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.santa.PortConfiguration;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class RestUtilitaires implements FactoryBean<User> {


    private PortConfiguration portConfiguration;

    RestUtilitaires(PortConfiguration portConfiguration) {
        this.portConfiguration = portConfiguration;
    }

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new DateFormatAdapter())
            .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeFormatAdapter())
            .create();

    public User getUtilisateur() {
        GsonConverterFactory factory = GsonConverterFactory.create(gson);
        HeaderInterceptor interceptor = new HeaderInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                // décommenter cette ligne pour utiliser fiddler ;o)
                //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                .addNetworkInterceptor(interceptor)
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://localhost:" + portConfiguration.getPort())
                .addConverterFactory(factory)
                .build();

        IRestClient restClient = retrofit.create(IRestClient.class);
        return new User(restClient, interceptor);
    }

    @Override
    public User getObject() throws Exception {
        return getUtilisateur();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
