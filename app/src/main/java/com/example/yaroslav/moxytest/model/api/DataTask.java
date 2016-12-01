package com.example.yaroslav.moxytest.model.api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.yaroslav.moxytest.presentation.callbacks.AsyncTaskCallback;

/**
 * Created by yaroslav on 14.11.16.
 */

public class DataTask extends AsyncTask<Void, Integer, Void> {
    private static final String LOG_TAG = DataTask.class.getSimpleName();
    private AsyncTaskCallback mCallback;

    public DataTask(AsyncTaskCallback callback) {
        this.mCallback = callback;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int i = 0;
        while (i++ < 15) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5){
                publishProgress(i);
            }
            Log.d(LOG_TAG, " i=" + i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        mCallback.onProgressUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
