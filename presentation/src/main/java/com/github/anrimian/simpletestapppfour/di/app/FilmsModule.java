package com.github.anrimian.simpletestapppfour.di.app;


import android.support.annotation.NonNull;

import com.github.anrimian.data.database.AppDatabase;
import com.github.anrimian.data.repositories.FilmsRepositoryImpl;
import com.github.anrimian.domain.business.FilmsInteractor;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.domain.repositories.FilmsRepository;
import com.github.anrimian.simpletestapppfour.ui.FilmsPresenter;
import com.github.anrimian.simpletestapppfour.utils.error.parser.ErrorParser;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

import static com.github.anrimian.simpletestapppfour.di.app.SchedulerModule.DB_SCHEDULER;
import static com.github.anrimian.simpletestapppfour.di.app.SchedulerModule.UI_SCHEDULER;

/**
 * Created on 02.11.2017.
 */

@Module
class FilmsModule {

    @Provides
    @NonNull
    FilmsPresenter provideFilmsPresenter(FilmsInteractor filmsInteractor,
                                         @Named(UI_SCHEDULER) Scheduler scheduler,
                                         ErrorParser errorParser) {
        return new FilmsPresenter(filmsInteractor, scheduler, errorParser);
    }

    @Provides
    @NonNull
    FilmsInteractor provideFilmsInteractor(FilmsRepository filmsRepository) {
        return new FilmsInteractor(filmsRepository);
    }

    @Provides
    @NonNull
    @Singleton
    FilmsRepository provideFilmsRepository(AppDatabase appDatabase,
                                           @Named(DB_SCHEDULER) Scheduler scheduler,
                                           List<Film> initialData) {
        return new FilmsRepositoryImpl(appDatabase, scheduler, initialData);
    }

    @Provides
    @Nonnull
    List<Film> provideInitialData() {
        List<Film> fakeFilms = new ArrayList<>();
        Film one = new Film();
        one.setId(1);
        one.setName("one name");
        one.setDescription("one description");
        one.setYear(1991);
        fakeFilms.add(one);

        Film two = new Film();
        two.setId(2);
        two.setName("two name");
        two.setDescription("two description");
        two.setYear(2015);
        fakeFilms.add(two);

        Film three = new Film();
        three.setId(3);
        three.setName("three name");
        three.setDescription("three description");
        three.setYear(2000);
        fakeFilms.add(three);

        Film four = new Film();
        four.setId(4);
        four.setName("four name");
        four.setDescription("four description");
        four.setYear(2019);
        fakeFilms.add(four);

        Film five = new Film();
        five.setId(5);
        five.setName("five name");
        five.setDescription("five description");
        five.setYear(1939);
        fakeFilms.add(five);

        Film six = new Film();
        six.setId(6);
        six.setName("six name");
        six.setDescription("six description");
        six.setYear(2000);
        fakeFilms.add(six);

        Film seven = new Film();
        seven.setId(7);
        seven.setName("seven name");
        seven.setDescription("seven description");
        seven.setYear(2019);
        fakeFilms.add(seven);

        Film eight = new Film();
        eight.setId(8);
        eight.setName("eight name");
        eight.setDescription("eight description");
        eight.setYear(2019);
        fakeFilms.add(eight);
        return fakeFilms;
    }
}
