package com.zeygame.javamvvm.network;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.SearchingModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIService {
    @GET
    Observable<DetailsModel> getMovieDetails(@Url() String name);

    @GET
    Observable<SearchingModel> searchMovie(@Url String name);

    //     Call<DetailsModel> getMovieDetails(@Header("Content-Type") String cType, @Url String get)

}
