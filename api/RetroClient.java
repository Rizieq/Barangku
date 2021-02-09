package com.rizieq.barangku.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {


    private static final String base_url = "http://192.168.1.5/db_barangku/";
    private static Retrofit retro;


    public static Retrofit getClient ()
    {
        if (retro == null)
        {
            retro = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }

    public static ApiRequest getRequestService() {
        return getClient().create(ApiRequest.class);
    }
}
