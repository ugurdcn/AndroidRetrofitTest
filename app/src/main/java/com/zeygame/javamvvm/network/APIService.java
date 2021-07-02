package com.zeygame.javamvvm.network;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.SearchingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {
    @GET
    Call<DetailsModel> getMovieList(@Url() String name);

    @GET
    Call<SearchingModel> search(@Url String name);

    //     Call<DetailsModel> getMovieList(@Header("Content-Type") String cType, @Url String get)

}
