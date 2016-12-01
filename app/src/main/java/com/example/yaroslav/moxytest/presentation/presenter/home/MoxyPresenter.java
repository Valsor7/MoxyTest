package com.example.yaroslav.moxytest.presentation.presenter.home;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.yaroslav.moxytest.application.MyApp;
import com.example.yaroslav.moxytest.model.api.DataTask;
import com.example.yaroslav.moxytest.model.api.Feed;
import com.example.yaroslav.moxytest.model.api.facebookAPI.FeedsApi;
import com.example.yaroslav.moxytest.presentation.callbacks.AsyncTaskCallback;
import com.example.yaroslav.moxytest.presentation.callbacks.RESTCallback;
import com.example.yaroslav.moxytest.presentation.view.home.MoxyView;
import com.example.yaroslav.moxytest.utils.SharedPrefs;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
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

    private FacebookCallback<LoginResult> requestFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult result) {
            mFeedsApi.feedsRequest(result.getAccessToken(), new RESTCallback<List<Feed>>() {
                @Override
                public void onError(String msg) {
                    getViewState().showError(msg);
                }

                @Override
                public void onResult(List<Feed> result) {
                    Log.d(TAG, result.toString());
                    getViewState().setFeeds(result);
                }
            });
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
        mFeedsApi = new FeedsApi();
    }

    public void loginFacebook() {
        if (AccessToken.getCurrentAccessToken().isExpired()) {
            mFacebookCallbackManager = CallbackManager.Factory.create();
            LoginManager manager = LoginManager.getInstance();
            manager.registerCallback(mFacebookCallbackManager, requestFacebookCallback);
            manager.logInWithReadPermissions(mActivity, Arrays.asList("email", "user_posts"));
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setLike() {

    }
}
