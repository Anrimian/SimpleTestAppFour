package com.github.anrimian.simpletestapppfour.ui.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.anrimian.domain.models.Film;
import com.github.anrimian.simpletestapppfour.R;

import java.util.List;

/**
 * Created on 15.6.17. It is awesome java class.
 */

public class FilmsAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private final List<Film> films;

    public FilmsAdapter(List<Film> films) {
        this.films = films;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_film, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.bind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
}
