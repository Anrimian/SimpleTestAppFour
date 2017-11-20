package com.github.anrimian.data.repositories;

import com.github.anrimian.data.database.AppDatabase;
import com.github.anrimian.data.database.dao.FilmsDao;
import com.github.anrimian.data.mappers.FilmsMapper;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.domain.repositories.FilmsRepository;

import org.mapstruct.factory.Mappers;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created on 20.11.2017.
 */

public class FilmsRepositoryImpl implements FilmsRepository {

    private FilmsDao filmsDao;
    private Scheduler dbScheduler;

    private FilmsMapper filmsMapper = Mappers.getMapper(FilmsMapper.class);

    public FilmsRepositoryImpl(AppDatabase appDatabase, Scheduler dbScheduler, List<Film> initialData) {
        this.filmsDao = appDatabase.filmsDao();
        this.dbScheduler = dbScheduler;
        setFilms(initialData);
    }

    @Override
    public Single<List<Film>> getFilms(@Nullable String searchQuery) {
        return Single.fromCallable(() -> filmsDao.getFilms())
                .map(filmsMapper::toFilms)
                .subscribeOn(dbScheduler);
    }

    private void setFilms(List<Film> initialData) {
        Completable.fromRunnable(() -> {
            filmsDao.deleteAll();
            filmsDao.insertAll(filmsMapper.toFilmEntityList(initialData));
        }).subscribeOn(dbScheduler).subscribe();

    }
}
