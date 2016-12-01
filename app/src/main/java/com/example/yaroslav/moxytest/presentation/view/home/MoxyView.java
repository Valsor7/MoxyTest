package com.example.yaroslav.moxytest.presentation.view.home;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.yaroslav.moxytest.model.api.Feed;

import java.util.List;

public interface MoxyView extends MvpView {
    void setFeeds(List<Feed> feeds);
    void showError(String msg);
}
