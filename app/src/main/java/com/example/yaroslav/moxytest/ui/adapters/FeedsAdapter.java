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
    public interface ClickFeedListener {
        void setLike();
    }

    private List<Feed> mFeeds;
    private ClickFeedListener mClickListener;

    public FeedsAdapter(ClickFeedListener clickListener) {
        this.mFeeds = new ArrayList<>();
        mClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View feedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new FeedVH(feedView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FeedVH feedVH = (FeedVH) holder;
        feedVH.bind(mFeeds.get(position), position);
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

        void bind(final Feed feed, int position) {
            Glide.with(mView.getContext())
                    .load(feed.pictureUrl)
                    .fitCenter()
                    .into(imgViewPicture);

            imgViewPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   mClickListener.showFeedDetails(feed);
                }
            });

//            textViewLike.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mClickListener.setLike();
//                }
//            });

//            tvDescription.setText(feed.getDescription());

            likeViewFb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: ");
                    Toast.makeText(view.getContext(), "dsfs", Toast.LENGTH_SHORT).show();
                    mClickListener.setLike();
                }
            });
            tvName.setText(feed.getName());
            Log.d("pos", ""+position);
            Like like = feed.getLike();
            if (like != null)
                textViewLikesCounter.setText(String.valueOf(like.getLikeUsers().size()));
        }
    }
}
