package com.iak.belajar.mymovie;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iak.belajar.mymovie.restapi.RestAPIInterface;
import com.iak.belajar.mymovie.restapi.RestAPIURL;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static App instance;
    private static Retrofit retrofit;
    private EventBus eventBus;
    private Gson gson;

    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createEventBus();
        createRetrofitClient();
        createGson();
    }

    private void createGson() {
        gson = new GsonBuilder().create();
    }
    private void createRetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(RestAPIURL.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofitClient() {
        return retrofit;
    }

    private void createEventBus() {
        eventBus = EventBus.builder()
                .logNoSubscriberMessages(false)
                .sendNoSubscriberEvent(false)
                .build();
    }

    public Gson getGson() {
        return gson;
    }

    public static App getInstance() {
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public RestAPIInterface getApiService() {
        return getRetrofitClient().create(RestAPIInterface.class);
    }
}