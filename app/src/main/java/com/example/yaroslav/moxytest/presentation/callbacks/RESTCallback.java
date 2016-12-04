package com.example.yaroslav.moxytest.presentation.callbacks;

import android.telecom.Call;

import com.example.yaroslav.moxytest.model.api.Feed;

import java.util.List;

/**
 * Created by yaroslav on 14.11.16.
 */

public interface RESTCallback<T> {
    void onError(String msg);
    void onResult(T data);
}
