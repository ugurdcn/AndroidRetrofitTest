package com.zeygame.javamvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.model.MovieModel;
import com.zeygame.javamvvm.model.SearchingModel;
import com.zeygame.javamvvm.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


@HiltViewModel
public class MovieViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<List<MovieModel>> movieList = new MutableLiveData<>();
    private final MutableLiveData<DetailsModel> movieDetail = new MutableLiveData<>();
    private Repository repository;


    //    public MovieViewModel(){
//        movieList = new MutableLiveData<>();
//    }
    @Inject
    public MovieViewModel(Repository repository) {
        this.repository = repository;
    }

//    public MovieViewModel() {
//    }

    public MutableLiveData<List<MovieModel>> getMovieListObserver() { return movieList; }
    public MutableLiveData<DetailsModel> getDetailObserver(){ return movieDetail; }

    public void searchMovie(String name){
        disposable.add(repository.searchMovie(name)
                .subscribeOn(Schedulers.io())
                .map(searchingModel -> {
//                    movieList.postValue(searchingModel.getSearch());
                    return searchingModel;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result->movieList.postValue(result.getSearch())
                        ,error-> Log.d("DENEME", "searchMovieError: "+error)));
    }
    public void getMovieDetails(String name){
        disposable.add(repository.getDetails(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(detailsModel -> {
//                    movieDetail.postValue(detailsModel);
                    return detailsModel;
                })
                .subscribe(movieDetail::postValue,error-> Log.d("DENEME"
                        , "get DetailsError: "+error)));
    }




//    APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
//
//    public void getDetails(String name, IResponseListener responseListener){
//        Call<DetailsModel> call = apiService.getMovieDetails("?apikey=790486e3&t="+name);
//
//        call.enqueue(new Callback<DetailsModel>() {
//            @Override
//            public void onResponse(@NotNull Call<DetailsModel> call, @NotNull Response<DetailsModel> response) {
//                if (response.code() == 200){
//                    responseListener.onSuccess(response.body());
//                }
//            }
//            @Override
//            public void onFailure(@NotNull Call<DetailsModel> call, @NotNull Throwable t) {
//                responseListener.onError(t.getMessage());
//                Log.d("İŞLEM HATASI: ",""+t);
//            }
//        });
//    }
//
//    public void search(String name){
//        Call<SearchingModel> call = apiService.searchMovie("?apikey=790486e3&s="+name);
//        call.enqueue(new Callback<SearchingModel>() {
//            @Override
//            public void onResponse(@NotNull Call<SearchingModel> call, @NotNull Response<SearchingModel> response) {
//
//                System.out.println("\n\nTamamdır: "+response.body());
//                System.out.println("Search: "+response.body().getSearch());
//                System.out.println("TotalResults: "+response.body().getTotalResults());
//                System.out.println("Response: "+response.body().getResponse());
//                System.out.println("\n\n");
//
//                movieList.postValue(response.body().getSearch());
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<SearchingModel> call, @NotNull Throwable t) {
//                Log.d("SONUÇ: ", ""+t.getMessage());
//            }
//        });
//    }

    public interface IResponseListener {
        <T> void onSuccess(T model);

        void onError(String error);
    }
}
