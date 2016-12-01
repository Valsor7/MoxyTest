package com.example.yaroslav.moxytest.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yaroslav on 11.11.16.
 */

public class Feed implements Parcelable{
    @SerializedName("id")
    public String id;
    @SerializedName("created_time")
    public String createdTime;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("full_picture")
    public String pictureUrl;
    @SerializedName("likes")
    public Like like;

    protected Feed(Parcel in) {
        id = in.readString();
        createdTime = in.readString();
        name = in.readString();
        description = in.readString();
        pictureUrl = in.readString();
        like = in.readParcelable(Like.class.getClassLoader());

    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "id='" + id + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(id);
        parcel.writeString(createdTime);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(pictureUrl);
        parcel.writeParcelable(like, flags);
    }
}
