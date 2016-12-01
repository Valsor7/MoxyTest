package com.example.yaroslav.moxytest.presentation.callbacks;

/**
 * Created by yaroslav on 14.11.16.
 */

public interface AsyncTaskCallback<T>{
    void onProgressUpdate(int progress);
    void onResult(T result);
}
