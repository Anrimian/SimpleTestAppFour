package com.github.anrimian.simpletestapppfour.utils.wrappers;

import android.support.v7.widget.SearchView;
import android.view.MenuItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static android.text.TextUtils.isEmpty;

/**
 * Created on 20.11.2017.
 */

public class SearchViewWrapper {

    @Nullable
    private SearchView searchView;
    private MenuItem searchItem;

    @Nullable
    private String searchText;

    public void setSearchView(@Nonnull SearchView searchView, @Nonnull MenuItem searchItem) {
        this.searchView = searchView;
        this.searchItem = searchItem;
        bindData();
//        searchView.setOnSearchClickListener(v -> searchView.setQuery(searchText, false));
    }

    public void bindText(String searchText) {
        this.searchText = searchText;
        bindData();
    }

    private void bindData() {
        if (searchView != null && !isEmpty(searchText)) {
            searchItem.expandActionView();
            searchView.setQuery(searchText, false);
            searchView.clearFocus();
        }
    }
}
