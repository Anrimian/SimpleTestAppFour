package com.github.anrimian.domain.repositories;

import com.github.anrimian.domain.models.Film;

import java.util.List;

import javax.annotation.Nullable;

import io.reactivex.Single;

/**
 * Created on 20.11.2017.
 */

public interface FilmsRepository {

    Single<List<Film>> getFilms(@Nullable String searchQuery);
}
