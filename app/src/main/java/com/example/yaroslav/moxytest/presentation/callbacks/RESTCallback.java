package com.example.yaroslav.moxytest.presentation.callbacks;

import android.telecom.Call;

/**
 * Created by yaroslav on 14.11.16.
 */

public interface RESTCallback<T> extends Callback<T> {
    void onError(String msg);
}
