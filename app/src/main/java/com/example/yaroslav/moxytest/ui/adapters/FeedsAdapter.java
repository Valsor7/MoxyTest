package com.example.yaroslav.moxytest.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yaroslav.moxytest.R;
import com.example.yaroslav.moxytest.model.api.Feed;
import com.example.yaroslav.moxytest.model.api.Like;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yaroslav on 11.11.16.
 */

public class FeedsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "FeedsAdapter";

    private List<Feed> mFeeds;

    public FeedsAdapter() {
        this.mFeeds = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View feedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new FeedVH(feedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FeedVH feedVH = (FeedVH) holder;
        feedVH.bind(mFeeds.get(position));
    }

    @Override
    public int getItemCount() {
        return mFeeds.size();
    }

    public List<Feed> getmFeeds() {
        return mFeeds;
    }

    public void setmFeeds(List<Feed> mFeeds) {
        this.mFeeds = mFeeds;
    }

    class FeedVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.iv_img)
        ImageView imgViewPicture;

        @BindView(R.id.tv_likes_counter)
        TextView textViewLikesCounter;

        @BindView(R.id.btn_like)
        Button likeViewFb;

        View mView;

        public FeedVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }

        void bind(Feed feed) {
            Glide.with(mView.getContext())
                    .load(feed.pictureUrl)
                    .fitCenter()
                    .into(imgViewPicture);

            tvName.setText(feed.getName());

            Like like = feed.getLike();
            if (like != null)
                textViewLikesCounter.setText(String.valueOf(like.getLikeUsers().size()));
        }
    }
}
