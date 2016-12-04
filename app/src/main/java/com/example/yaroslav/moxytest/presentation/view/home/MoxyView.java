package com.example.yaroslav.moxytest.presentation.view.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.yaroslav.moxytest.model.api.Feed;

import java.util.List;

public interface MoxyView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setFeeds(List<Feed> feeds);
    void showError(String msg);
}
