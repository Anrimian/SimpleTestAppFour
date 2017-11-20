package com.github.anrimian.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.github.anrimian.data.database.dao.FilmsDao;
import com.github.anrimian.data.database.models.FilmEntity;

/**
 * Created on 18.11.2017.
 */

@Database(entities = {FilmEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String FILMS = "films";

    public abstract FilmsDao filmsDao();
}
