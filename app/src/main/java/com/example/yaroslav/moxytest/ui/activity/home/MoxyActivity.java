package com.example.yaroslav.moxytest.ui.activity.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
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

    @InjectPresenter
    MoxyPresenter mMoxyPresenter;

    @ProvidePresenter
    MoxyPresenter provideMoxyPresenter() {
        return new MoxyPresenter(this);
    }

    @BindView(R.id.rv_feeds)
    RecyclerView mFeedsRecyclerView;

    private AlertDialog mDialog;

    private FeedsAdapter mFeedsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moxy);
        ButterKnife.bind(this);

        mFeedsAdapter = new FeedsAdapter();
        mFeedsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFeedsRecyclerView.setAdapter(mFeedsAdapter);
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
