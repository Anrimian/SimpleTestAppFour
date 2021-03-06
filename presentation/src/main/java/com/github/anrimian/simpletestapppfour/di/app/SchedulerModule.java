package com.github.anrimian.simpletestapppfour.di.app;

import android.support.annotation.NonNull;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 15.9.17. It is awesome java class.
 */

@Module
public class SchedulerModule {

    public static final String IO_SCHEDULER = "io_scheduler";
    public static final String DB_SCHEDULER = "db_scheduler";
    public static final String UI_SCHEDULER = "ui_scheduler";

    @Provides
    @NonNull
    @Named(IO_SCHEDULER)
    Scheduler provideIOScheduler() {
        return Schedulers.io();
    }

    @Provides
    @NonNull
    @Named(DB_SCHEDULER)
    Scheduler provideDBScheduler() {
        return Schedulers.computation();
    }

    @Provides
    @NonNull
    @Named(UI_SCHEDULER)
    Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
