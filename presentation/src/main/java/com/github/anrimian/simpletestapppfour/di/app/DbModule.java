package com.github.anrimian.simpletestapppfour.di.app;

import android.content.Context;

import com.github.anrimian.data.database.AppDatabase;
import com.github.anrimian.data.database.DatabaseManager;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created on 20.11.2017.
 */

@Module
public class DbModule {

    @Provides
    @Nonnull
    @Singleton
    DatabaseManager provideDatabaseManager(Context context) {
        return new DatabaseManager(context);
    }

    @Provides
    @Nonnull
    @Singleton
    AppDatabase provideAppDatabase(DatabaseManager databaseManager) {
        return databaseManager.getAppDatabase();
    }
}
