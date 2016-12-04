package com.example.yaroslav.moxytest.model.api.facebookAPI;

import android.os.Bundle;

import com.example.yaroslav.moxytest.model.api.Feed;
import com.example.yaroslav.moxytest.model.api.Like;
import com.example.yaroslav.moxytest.presentation.callbacks.RESTCallback;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yaroslav on 03.11.16.
 */

public class FeedsApi {

    public void feedsRequest(final AccessToken token, final RESTCallback<List<Feed>> responseCallback) {
        Bundle paramsBundle = new Bundle();
        paramsBundle.putString("fields", "full_picture,name,message,description,created_time,likes");

        new GraphRequest(token, "/me/feed", paramsBundle, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(final GraphResponse response) {
                List<Feed> feeds = parseJsonToFeeds(response.getJSONObject());
                if (!feeds.isEmpty()) {
                    responseCallback.onResult(feeds);
                } else
                    responseCallback.onError("Empty list");
            }
        }).executeAsync();
    }

    private List<Feed> parseJsonToFeeds(JSONObject jsonData) {
        List<Feed> feeds = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        try {
            JSONArray dataArray = jsonData.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                Feed feed = gson.fromJson(dataArray.get(i).toString(), Feed.class);
                if (feed.like == null){
                    feed.setLike(new Like());
                }
                feeds.add(feed);
            }
        } catch (JSONException | NullPointerException e) {
            return Collections.emptyList();
        }
        return feeds;
    }
}
