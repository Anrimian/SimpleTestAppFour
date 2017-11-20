package com.github.anrimian.domain.business;

import com.github.anrimian.domain.models.Film;
import com.github.anrimian.domain.repositories.FilmsRepository;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Single;

/**
 * Created on 20.11.2017.
 */

public class FilmsInteractor {

    private FilmsRepository filmsRepository;

    public FilmsInteractor(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    public Single<List<Film>> getFilms(@Nullable String query) {
        return filmsRepository.getFilms(query);
    }
}
