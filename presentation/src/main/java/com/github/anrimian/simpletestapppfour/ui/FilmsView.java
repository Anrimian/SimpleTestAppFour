package com.github.anrimian.simpletestapppfour.ui;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.github.anrimian.domain.models.Film;
import com.github.anrimian.simpletestapppfour.utils.error.ErrorCommand;
import com.github.anrimian.simpletestapppfour.utils.moxy.SingleStateByTagStrategy;

import java.util.List;

/**
 * Created on 20.11.2017.
 */

interface FilmsView extends MvpView {

    String LOADING_STATE = "loading_state";

    @StateStrategyType(value = SingleStateByTagStrategy.class, tag = LOADING_STATE)
    void showProgress();

    @StateStrategyType(value = SingleStateByTagStrategy.class, tag = LOADING_STATE)
    void showComplete();

    @StateStrategyType(value = SingleStateByTagStrategy.class, tag = LOADING_STATE)
    void showEmptyState();

    @StateStrategyType(value = SingleStateByTagStrategy.class, tag = LOADING_STATE)
    void showStateError(ErrorCommand errorInfo);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void bindFilmList(List<Film> films);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void bindSearchText(String searchQuery);
}
