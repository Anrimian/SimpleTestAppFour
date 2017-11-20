package com.github.anrimian.data.repositories;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.github.anrimian.data.database.AppDatabase;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.domain.repositories.FilmsRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created on 20.11.2017.
 */
public class FilmsRepositoryImplTest {

    private FilmsRepository filmsRepository;

    private TestScheduler testScheduler = new TestScheduler();

    private Film one = new Film();
    private Film two = new Film();
    private Film three = new Film();
    private Film four = new Film();
    private List<Film> fakeFilms = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        one.setId(1);
        one.setName("one name");
        one.setDescription("one description");
        one.setYear(1991);
        fakeFilms.add(one);

        two.setId(2);
        two.setName("two name");
        two.setDescription("two description");
        two.setYear(2015);
        fakeFilms.add(two);

        three.setId(3);
        three.setName("three name");
        three.setDescription("three description");
        three.setYear(2000);
        fakeFilms.add(three);

        four.setId(4);
        four.setName("four name");
        four.setDescription("four description");
        four.setYear(2019);
        fakeFilms.add(four);

        Context appContext = InstrumentationRegistry.getTargetContext();
        AppDatabase appDatabase = Room.inMemoryDatabaseBuilder(appContext, AppDatabase.class).build();

        filmsRepository = new FilmsRepositoryImpl(appDatabase, testScheduler, fakeFilms);
    }

    @Test
    public void getAllFilmsTest() throws Exception {
        TestObserver<Film> testObserver = filmsRepository.getFilms(null)
                .toObservable()
                .flatMapIterable(films -> films)
                .test();

        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValues(one, three, two, four);
    }

    @Test
    public void findFilmsTest() throws Exception {
        TestObserver<Film> testObserver = filmsRepository.getFilms("four")
                .toObservable()
                .flatMapIterable(films -> films)
                .test();

        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS);

        testObserver.assertNoErrors();
        testObserver.assertValue(four);
    }

}