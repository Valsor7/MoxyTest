package com.example.yaroslav.moxytest.presentation.presenter.home;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.yaroslav.moxytest.model.api.Feed;
import com.example.yaroslav.moxytest.model.api.facebookAPI.FeedsApi;
import com.example.yaroslav.moxytest.presentation.callbacks.RESTCallback;
import com.example.yaroslav.moxytest.presentation.view.home.MoxyView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.List;


@InjectViewState
public class MoxyPresenter extends MvpPresenter<MoxyView> {
    private static final String TAG = MoxyPresenter.class.getSimpleName();
    private Activity mActivity;

    private CallbackManager mFacebookCallbackManager;
    private FeedsApi mFeedsApi;
    private RESTCallback<List<Feed>> requestRestCallback = new RESTCallback<List<Feed>>() {
        @Override
        public void onError(String msg) {
            getViewState().showError(msg);
        }

        @Override
        public void onResult(List<Feed> result) {
            Log.e(TAG, "fetched");
            getViewState().setFeeds(result);
        }
    };

    private FacebookCallback<LoginResult> requestFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult result) {
            mFeedsApi.feedsRequest(result.getAccessToken(), requestRestCallback);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            getViewState().showError(error.getMessage());
        }
    };

    public MoxyPresenter() {
        Log.e(TAG, "presenter constructor");
        mFeedsApi = new FeedsApi();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPosts();
    }

    private void loginFacebook() {
        if (!isUserLoggedIn()) {
            mFacebookCallbackManager = CallbackManager.Factory.create();
            LoginManager manager = LoginManager.getInstance();
            manager.registerCallback(mFacebookCallbackManager, requestFacebookCallback);
            manager.logInWithReadPermissions(mActivity, Arrays.asList("email", "user_posts"));
        }
    }

    public void loadPosts(){
        if (isUserLoggedIn()){
            mFeedsApi.feedsRequest(AccessToken.getCurrentAccessToken(), requestRestCallback);
        } else {
            loginFacebook();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        LoginManager.getInstance().logOut();
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setLike() {

    }

    private boolean isUserLoggedIn(){
        AccessToken token = AccessToken.getCurrentAccessToken();
        return token != null && !token.isExpired();
    }
}
