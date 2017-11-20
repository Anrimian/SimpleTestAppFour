package com.github.anrimian.simpletestapppfour.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.simpletestapppfour.R;
import com.github.anrimian.simpletestapppfour.di.Components;
import com.github.anrimian.simpletestapppfour.ui.view.FilmsAdapter;
import com.github.anrimian.simpletestapppfour.utils.AndroidUtils;
import com.github.anrimian.simpletestapppfour.utils.error.ErrorCommand;
import com.github.anrimian.simpletestapppfour.utils.wrappers.ProgressViewWrapper;
import com.github.anrimian.simpletestapppfour.utils.wrappers.SearchViewWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmsActivity extends MvpAppCompatActivity implements FilmsView {

    @InjectPresenter
    FilmsPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchViewWrapper searchViewWrapper = new SearchViewWrapper();
    private ProgressViewWrapper progressViewWrapper;
    private FilmsAdapter filmsAdapter;

    @ProvidePresenter
    FilmsPresenter providePresenter() {
        return Components.getAppComponent().filmsPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressViewWrapper = new ProgressViewWrapper(view);
        progressViewWrapper.setTryAgainButtonOnClickListener(v -> presenter.onTryAgainButtonClicked());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_films, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchViewWrapper.setSearchView(searchView, searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onStartSearchButtonClicked(query);
                AndroidUtils.hideKeyboard(FilmsActivity.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.onSearchTextChanged(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void bindFilmList(List<Film> films) {
        filmsAdapter = new FilmsAdapter(films);
        recyclerView.setAdapter(filmsAdapter);
    }

    @Override
    public void bindSearchText(String searchQuery) {
        searchViewWrapper.bindText(searchQuery);
    }

    @Override
    public void showProgress() {
        progressViewWrapper.showProgress();
    }

    @Override
    public void showComplete() {
        progressViewWrapper.hideAll();
    }

    @Override
    public void showEmptyState() {
        progressViewWrapper.showMessage(R.string.not_found, false);
    }

    @Override
    public void showStateError(ErrorCommand errorCommand) {
        progressViewWrapper.showMessage(errorCommand.getMessage(), true);
    }

    @Override
    public void updateList() {
        filmsAdapter.notifyDataSetChanged();
    }
}
