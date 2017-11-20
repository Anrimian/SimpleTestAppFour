package com.github.anrimian.data.mappers;

import com.github.anrimian.data.database.models.FilmEntity;
import com.github.anrimian.domain.models.Film;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created on 19.11.2017.
 */

@Mapper
public interface FilmsMapper {

    Film toFilm(FilmEntity FilmEntity);

    FilmEntity toFilmEntity(Film Film);

    @IterableMapping(elementTargetType = Film.class)
    List<Film> toFilms(List<FilmEntity> FilmEntities);

    @IterableMapping(elementTargetType = FilmEntity.class)
    List<FilmEntity> toFilmEntityList(List<Film> Films);
}
