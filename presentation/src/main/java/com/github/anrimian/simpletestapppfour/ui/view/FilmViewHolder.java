package com.github.anrimian.simpletestapppfour.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.anrimian.domain.models.Film;
import com.github.anrimian.simpletestapppfour.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 15.6.17. It is awesome java class.
 */

class FilmViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_year)
    TextView tvYear;

    @BindView(R.id.card_view)
    View cardView;

    FilmViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Film film) {
        tvName.setText(film.getName());
        tvDescription.setText(film.getDescription());
        tvYear.setText(String.valueOf(film.getYear()));
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
