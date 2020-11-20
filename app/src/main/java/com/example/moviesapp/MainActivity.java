package com.example.moviesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.Adapter.MovieListAdapter;
import com.example.moviesapp.Model.MovieModel;
import com.example.moviesapp.ViewModel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.itemclicklistener {
    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opendialog().show();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        TextView resulttv = findViewById(R.id.noresulttv);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);
        viewmodel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewmodel.getmovieslistobserver().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {

                if (movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovielist(movieModels);
                    resulttv.setVisibility(View.GONE);
                } else {
                    resulttv.setVisibility(View.VISIBLE);
                }

            }
        });
        viewmodel.makeapicall();
    }

    androidx.appcompat.app.AlertDialog opendialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle("Məlumatlandırma")
                .setMessage("Bu Proqram Təminatı\nRetrofit2,Live Data,Recyclerview,Glide\n" +
                        "komponentlərindən istifadə edilərək\nMVVM arxitekturasına uyğun yığılmışdır.")
                .setPositiveButton("Oxudum", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }


    @Override
    public void onmovieclick(MovieModel movie) {
        Toast.makeText(this, "Kliklədiyiniz Filmin Adı:" + movie.getTitle(), Toast.LENGTH_LONG).show();
    }
}