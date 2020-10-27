package com.example.apifilms.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.apifilms.data.models.FilmModel;

import java.util.List;

@Dao

public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveFilm(FilmModel filmModel);

    @Query("SELECT * FROM FilmModel")
    List<FilmModel>getFilms();
}
