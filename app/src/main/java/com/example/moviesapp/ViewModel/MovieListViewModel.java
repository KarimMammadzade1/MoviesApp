package com.example.moviesapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.Network.APIService;
import com.example.moviesapp.Network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> movieslist;

    public MovieListViewModel() {
        movieslist = new MutableLiveData<>();

    }

    public MutableLiveData<List<MovieModel>> getmovieslistobserver() {

        return movieslist;
    }


    public void makeapicall() {
        APIService apiService = RetroInstance.getRetroclient().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getmovieslist();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                movieslist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                movieslist.postValue(null);
            }
        });


    }
}
