package com.zeygame.javamvvm.repository;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.SearchingModel;
import com.zeygame.javamvvm.network.APIService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private static final String TAG="REPO";
    APIService apiService;

    @Inject
    public Repository(APIService apiService){
        this.apiService = apiService;
    }
    public Observable<DetailsModel> getDetails(String name){
        return apiService.getMovieDetails("?apikey=790486e3&t="+name);
    }
    @Singleton
    public Observable<SearchingModel> searchMovie(String name){
        return apiService.searchMovie("?apikey=790486e3&s="+name);
    }
}
