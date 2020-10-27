package com.example.apifilms.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.apifilms.data.models.FilmModel;

@Database(entities = {FilmModel.class},version = 1)

public abstract class FilmDatabase extends RoomDatabase {
    public abstract FilmDao filmDao();
}
