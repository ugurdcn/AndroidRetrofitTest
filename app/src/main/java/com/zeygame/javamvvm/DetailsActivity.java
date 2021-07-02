package com.zeygame.javamvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeygame.javamvvm.model.DetailsModel;
import com.zeygame.javamvvm.network.ImageProccess;
import com.zeygame.javamvvm.viewmodel.MovieViewModel;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imgPoster;
    private TextView txTitle, txGenre,txDirector,txWriter,txActors,txImdbRating,txPlot;
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initialize();
    }

    private void initialize() {
        imgPoster = findViewById(R.id.imgPoster);
        txTitle = findViewById(R.id.txDetailTitle);
        txGenre = findViewById(R.id.txGenre);
        txDirector = findViewById(R.id.txDirector);
        txWriter = findViewById(R.id.txWriter);
        txActors = findViewById(R.id.txActors);
        txImdbRating = findViewById(R.id.txImdbRatings);
        txPlot = findViewById(R.id.txPlot);

        getMovieDetails(getIntent().getStringExtra("name"));
    }
    private void getMovieDetails(String name){
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getDetails(name, new MovieViewModel.IResponseListener() {
            @Override
            public <T> void onSuccess(T model) {
                if (model instanceof DetailsModel) setFields((DetailsModel) model);
            }
            @Override
            public void onError(String error) {
                Toast.makeText(DetailsActivity.this, "Hata: "+error , Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setFields(DetailsModel detail){
        new ImageProccess().initImage(detail.getPoster(),this,imgPoster);
        txTitle.setText(detail.getTitle());
        txGenre.setText("Tür: "+detail.getGenre());
        txDirector.setText("Yönetmen: "+detail.getDirector());
        txActors.setText("Oyuncular: "+detail.getActors());
        txWriter.setText("Senarist: "+detail.getWriter());
        txImdbRating.setText("IMDB: "+detail.getImdbRating()+" , "+detail.getImdbVotes()+" Oy");
        txPlot.setText(detail.getPlot());
    }
}