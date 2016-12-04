package com.example.yaroslav.moxytest.ui.activity.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.yaroslav.moxytest.R;
import com.example.yaroslav.moxytest.model.api.Feed;
import com.example.yaroslav.moxytest.presentation.presenter.home.MoxyPresenter;
import com.example.yaroslav.moxytest.presentation.view.home.MoxyView;
import com.example.yaroslav.moxytest.ui.adapters.FeedsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoxyActivity extends MvpAppCompatActivity implements MoxyView {
    public static final String TAG = "MoxyActivity";
    private static final int SPAN_COUNT = 2;

    FeedsAdapter.ClickFeedListener mClickFeedListener = new FeedsAdapter.ClickFeedListener() {
        @Override
        public void setLike() {
            mMoxyPresenter.setLike();
        }
    };

    @InjectPresenter
    MoxyPresenter mMoxyPresenter;

    @BindView(R.id.rv_feeds)
    RecyclerView mFeedsRecyclerView;

    @BindView(R.id.switch_layout)
    SwitchCompat mSwitcher;

    FeedsAdapter mFeedsAdapter;
    private AlertDialog mDialog;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MoxyActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_moxy);
        ButterKnife.bind(this);

        mMoxyPresenter.setmActivity(this);
        mFeedsAdapter = new FeedsAdapter(mClickFeedListener);
        mFeedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFeedsRecyclerView.setAdapter(mFeedsAdapter);

        Log.e(TAG, "onCreate: is first" + mMoxyPresenter.isInRestoreState(this));

        mSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    mFeedsRecyclerView.setLayoutManager(new GridLayoutManager(MoxyActivity.this, SPAN_COUNT));
                } else {
                    mFeedsRecyclerView.setLayoutManager(new LinearLayoutManager(MoxyActivity.this));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mMoxyPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setFeeds(List<Feed> feeds) {
        mFeedsAdapter.setmFeeds(feeds);
        mFeedsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        Log.d(TAG, "=======>" + msg);
        mDialog = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(msg)
                .create();
        mDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
