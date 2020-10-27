package com.example.apifilms.ui.saveactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.apifilms.App;
import com.example.apifilms.R;
import com.example.apifilms.adepter.FilmAdapter;
import com.example.apifilms.adepter.SaveFilmAdepter;
import com.example.apifilms.data.models.FilmModel;
import com.example.apifilms.ui.activitynew.ActivityNew;

import java.util.ArrayList;

public class SaveActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SaveFilmAdepter adepter;
    ArrayList<FilmModel>filmModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        recyclerView = findViewById(R.id.recycler_save_film);
        adepter = new SaveFilmAdepter();
        recyclerView.setAdapter(adepter);
        getFilms();
        adepter.setOnClick(new FilmAdapter.OnClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(SaveActivity.this, ActivityNew.class);
                intent.putExtra("saveFilm",filmModelArrayList.get(position).getId());
                startActivity(intent);
            }
        });


    }
    private void getFilms(){
        filmModelArrayList = (ArrayList<FilmModel>)App.filmDatabase.filmDao().getFilms();
        adepter.setFilmModels(filmModelArrayList);
    }

}