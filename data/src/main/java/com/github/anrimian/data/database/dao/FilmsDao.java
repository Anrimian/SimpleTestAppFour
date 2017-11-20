package com.github.anrimian.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.github.anrimian.data.database.models.FilmEntity;

import java.util.List;

/**
 * Created on 18.11.2017.
 */

@Dao
public interface FilmsDao {

    @Query("SELECT * FROM films")
    List<FilmEntity> getFilms();

    @Insert
    void insertAll(List<FilmEntity> films);

    @Query("DELETE FROM films")
    void deleteAll();
}
