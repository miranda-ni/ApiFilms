package com.example.apifilms;

import android.app.Application;

import androidx.room.Room;

import com.example.apifilms.data.local.FilmDatabase;
import com.example.apifilms.data.netwokr.GhibliService;

public class App extends Application {

    public static GhibliService ghibliService;
    public static FilmDatabase filmDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        filmDatabase = Room.databaseBuilder(this,FilmDatabase.class,
                "filmDatabase").allowMainThreadQueries().build();
        ghibliService = new GhibliService();


    }
}
