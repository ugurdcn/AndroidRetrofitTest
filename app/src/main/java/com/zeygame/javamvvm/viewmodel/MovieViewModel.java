package com.zeygame.javamvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.MovieModel;
import com.zeygame.javamvvm.model.SearchingModel;
import com.zeygame.javamvvm.network.APIService;
import com.zeygame.javamvvm.network.RetroInstance;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> movieList;

    public MovieViewModel(){
        movieList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMovieListObserver(){
        return movieList;
    }
    APIService apiService = RetroInstance.getRetroClient().create(APIService.class);

    public void getDetails(String name, IResponseListener responseListener){
        Call<DetailsModel> call = apiService.getMovieList("?apikey=790486e3&t="+name);

        call.enqueue(new Callback<DetailsModel>() {
            @Override
            public void onResponse(@NotNull Call<DetailsModel> call, @NotNull Response<DetailsModel> response) {
                if (response.code() == 200){
                    responseListener.onSuccess(response.body());
                }
            }
            @Override
            public void onFailure(@NotNull Call<DetailsModel> call, @NotNull Throwable t) {
                responseListener.onError(t.getMessage());
                Log.d("İŞLEM HATASI: ",""+t);
            }
        });
    }

    public void search(String name){
        Call<SearchingModel> call = apiService.search("?apikey=790486e3&s="+name);
        call.enqueue(new Callback<SearchingModel>() {
            @Override
            public void onResponse(@NotNull Call<SearchingModel> call, @NotNull Response<SearchingModel> response) {

                System.out.println("\n\nTamamdır: "+response.body());
                System.out.println("Search: "+response.body().getSearch());
                System.out.println("TotalResults: "+response.body().getTotalResults());
                System.out.println("Response: "+response.body().getResponse());
                System.out.println("\n\n");

                movieList.postValue(response.body().getSearch());
            }

            @Override
            public void onFailure(@NotNull Call<SearchingModel> call, @NotNull Throwable t) {
                Log.d("SONUÇ: ", ""+t.getMessage());
            }
        });
    }

    public interface IResponseListener{
        <T>void onSuccess(T model);
        void onError(String error);
    }

}
