package com.example.apifilms.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apifilms.App;
import com.example.apifilms.R;
import com.example.apifilms.adepter.FilmAdapter;
import com.example.apifilms.data.models.FilmModel;
import com.example.apifilms.data.netwokr.GhibliService;
import com.example.apifilms.ui.activitynew.ActivityNew;
import com.example.apifilms.ui.saveactivity.SaveActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  private   RecyclerView recyclerView;
   private FilmAdapter filmAdapter;
   private ArrayList<FilmModel> filmModelArrayList;
   private Button saveFilms;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        filmAdapter = new FilmAdapter();
        recyclerView.setAdapter(filmAdapter);
        saveFilms = findViewById(R.id.saveFilms);
        saveFilms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SaveActivity.class));
            }
        });



        App.ghibliService.getFilms(new GhibliService.GhibliListCallBack() {
            @Override
            public void onSuccess(ArrayList<FilmModel> filmModels) {
                filmAdapter.SetFilmModel(filmModels);
                filmAdapter.setOnClick(new FilmAdapter.OnClick() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(MainActivity.this, ActivityNew.class);
                        intent.putExtra("key", filmModels.get(position).getId());
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onFailure(Throwable e) {
                Log.d("TAG", "onFailure: " + e.getMessage());

            }
        });



    }
}