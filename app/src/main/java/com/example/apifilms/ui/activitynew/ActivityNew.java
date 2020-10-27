package com.example.apifilms.ui.activitynew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apifilms.App;
import com.example.apifilms.R;
import com.example.apifilms.data.models.FilmModel;
import com.example.apifilms.data.netwokr.GhibliService;

public class ActivityNew extends AppCompatActivity {

   private TextView deck,director,realDate;
   private String position,position2;
    private Button saveLocal;
    private FilmModel filmModel1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        deck = findViewById(R.id.deck_two);
        director= findViewById(R.id.director_view);
        realDate = findViewById(R.id.rel_dat);
        saveLocal = findViewById(R.id.save_local_btn);
        onClick();
        setPosition();


        App.ghibliService.getFilmById(position2, new GhibliService.GhibliFilmCallback() {
            @Override
            public void onSuccess(FilmModel filmModel) {
                if (filmModel != null){
                    Log.d("TAG", "onSuccess: " + filmModel.getTitle());
                    deck.setText(filmModel.getDescription());
                    director.setText(filmModel.getDirector());
                    realDate.setText(filmModel.getReleaseDate());
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                Log.d("TAG", "onFailure: " + ex);

            }
        });




        App.ghibliService.getFilmById(position, new GhibliService.GhibliFilmCallback() {
            @Override
            public void onSuccess(FilmModel filmModel) {
                if (filmModel != null){
                    filmModel1 = filmModel;

                deck.setText(filmModel.getDescription());
                director.setText(filmModel.getDirector());
                realDate.setText(filmModel.getReleaseDate());
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                Log.d("TAG", "onFailure: " + ex);
                

            }

        });

    }
    private void onClick(){
        saveLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.filmDatabase.filmDao().saveFilm(filmModel1);
                Log.d("TAG", "onClick: " + filmModel1);
            }
        });
    }
    private void setPosition(){
        if (getIntent() != null) {
            position = getIntent().getStringExtra("key");
            position2 = getIntent().getStringExtra("saveFilm");
        }
    }

}