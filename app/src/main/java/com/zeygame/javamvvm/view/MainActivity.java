package com.zeygame.javamvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zeygame.javamvvm.R;
import com.zeygame.javamvvm.adapter.MovieAdapter;
import com.zeygame.javamvvm.model.MovieModel;
import com.zeygame.javamvvm.viewmodel.MovieViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcMovies;
    private List<MovieModel> movieList;
    private MovieAdapter adapter;
    private MovieViewModel viewModel;
    private TextView txResult;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        rcMovies = findViewById(R.id.rcView);
        txResult = findViewById(R.id.txResult);
        etSearch = findViewById(R.id.etSearch);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        rcMovies.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(this, movieList, movie -> {
            /// Detay sayfası açılsın
            Intent i = new Intent(MainActivity.this,DetailsActivity.class);
            i.putExtra("name",movie.getTitle());
            startActivity(i);
//            Toast.makeText(this, "Film Adı "+ movie.getTitle(), Toast.LENGTH_SHORT).show();
        });
        rcMovies.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etSearch.getText().length()>2)getMovies(etSearch.getText().toString());
            }
        });
    }
    private void getMovies(String name){
        // Deprecated
//        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMovieListObserver().observe(this, list -> {
            if (list!=null){
                movieList = list;
                adapter.setMovieList(list);
                txResult.setVisibility(View.GONE);
            }else{
                txResult.setVisibility(View.VISIBLE);
                txResult.setText("Veri bulunamadı!");
            }
        });
        viewModel.searchMovie(name);
    }
}