package com.zeygame.javamvvm.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    public  static String BASE_URL = "https://velmm.com/apis/";
    public static String  detailUrl="https://www.omdbapi.com/?apikey=790486e3&t=";
    public static String  searchUrl="https://www.omdbapi.com";

    private static Retrofit retrofit;

    public static Retrofit getRetroClient(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(searchUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
