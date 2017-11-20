package com.github.anrimian.simpletestapppfour.ui;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.anrimian.domain.business.FilmsInteractor;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.simpletestapppfour.utils.error.ErrorCommand;
import com.github.anrimian.simpletestapppfour.utils.error.parser.ErrorParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * Created on 20.11.2017.
 */

@InjectViewState
public class FilmsPresenter extends MvpPresenter<FilmsView> {

    private static final int SEARCH_DELAY = 750;

    private FilmsInteractor filmsInteractor;
    private Scheduler uiScheduler;
    private ErrorParser errorParser;

    private final List<Film> films = new ArrayList<>();

    private String searchQuery;

    private Disposable startSearchDisposable;
    private Disposable loadingDisposable;

    public FilmsPresenter(FilmsInteractor filmsInteractor,
                          Scheduler uiScheduler,
                          ErrorParser errorParser) {
        this.filmsInteractor = filmsInteractor;
        this.uiScheduler = uiScheduler;
        this.errorParser = errorParser;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().bindFilmList(films);
        startLoading();
    }

    @Override
    public void attachView(FilmsView view) {
        super.attachView(view);
        view.bindSearchText(searchQuery);
    }

    void onTryAgainButtonClicked() {
        startLoading();
    }

    void onStartSearchButtonClicked(String searchQuery) {
        startSearch(searchQuery);
    }

    void onSearchTextChanged(String searchQuery) {
        if (startSearchDisposable != null) {
            startSearchDisposable.dispose();
        }
        startSearchDisposable = Observable.timer(SEARCH_DELAY, TimeUnit.MILLISECONDS)
                .observeOn(uiScheduler)
                .subscribe(o -> startSearch(searchQuery));
    }

    private void startLoading() {
        getViewState().showProgress();
        loadingDisposable = filmsInteractor.getFilms(searchQuery)
                .observeOn(uiScheduler)
                .subscribe(this::onLoadingCompete, this::onLoadingError);
    }

    private void onLoadingError(Throwable throwable) {
        ErrorCommand errorCommand = errorParser.parseError(throwable);
        getViewState().showStateError(errorCommand);
    }

    private void onLoadingCompete(List<Film> results) {
        films.clear();
        films.addAll(results);

        if (films.isEmpty()) {
            getViewState().showEmptyState();
        } else {
            getViewState().showComplete();
        }
        getViewState().updateList();
    }

    private void startSearch(String searchQuery) {
        this.searchQuery = searchQuery;

        if (loadingDisposable != null) {
            loadingDisposable.dispose();
            loadingDisposable = null;
        }
        if (startSearchDisposable != null) {
            startSearchDisposable.dispose();
            startSearchDisposable = null;
        }
        films.clear();
        getViewState().updateList();
        startLoading();
    }
}
