package com.example.apifilms.ui.activitynew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apifilms.App;
import com.example.apifilms.R;
import com.example.apifilms.data.models.FilmModel;
import com.example.apifilms.data.netwokr.GhibliService;

public class ActivityNew extends AppCompatActivity {

    TextView deck,director,realDate;
    String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        deck = findViewById(R.id.deck_two);
        director= findViewById(R.id.director_view);
        realDate = findViewById(R.id.rel_dat);


        if (getIntent() != null) {
            position = getIntent().getStringExtra("key");
        }

        App.ghibliService.getFilmById(position, new GhibliService.GhibliFilmCallback() {
            @Override
            public void onSuccess(FilmModel filmModel) {
                deck.setText(filmModel.getDescription());
                director.setText(filmModel.getDirector());
                realDate.setText(filmModel.getReleaseDate());
            }

            @Override
            public void onFailure(Throwable ex) {
                

            }
        });

    }

}