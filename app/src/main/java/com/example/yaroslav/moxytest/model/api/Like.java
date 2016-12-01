package com.example.yaroslav.moxytest.model.api;

import android.icu.lang.UScript;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yaroslav on 21.11.16.
 */

public class Like implements Parcelable{
    @SerializedName("data")
    List<User> likeUsers;

    public Like(){
        likeUsers = new ArrayList<>();
    }

    protected Like(Parcel in) {
        in.readList(likeUsers, ArrayList.class.getClassLoader());
    }

    public static final Creator<Like> CREATOR = new Creator<Like>() {
        @Override
        public Like createFromParcel(Parcel in) {
            return new Like(in);
        }

        @Override
        public Like[] newArray(int size) {
            return new Like[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(likeUsers);
    }

    public List<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<User> likeUsers) {
        this.likeUsers = likeUsers;
    }
}
