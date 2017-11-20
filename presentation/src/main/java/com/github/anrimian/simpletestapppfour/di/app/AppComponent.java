package com.github.anrimian.simpletestapppfour.di.app;


import com.github.anrimian.simpletestapppfour.ui.FilmsPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 11.02.2017.
 */

@Singleton
@Component(modules = { AppModule.class, SchedulerModule.class, ErrorModule.class, FilmsModule.class, DbModule.class })
public interface AppComponent {

    FilmsPresenter filmsPresenter();
}