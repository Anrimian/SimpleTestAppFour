package com.github.anrimian.data.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created on 18.11.2017.
 */

public class DatabaseManager {

    private static final String DATABASE_NAME = "test_films_database";

    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public AppDatabase getAppDatabase() {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }
}
