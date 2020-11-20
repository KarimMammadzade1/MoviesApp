package com.example.moviesapp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static String Base_Url = "https://velmm.com/apis/";

    public static Retrofit retrofit;

    public static Retrofit getRetroclient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
