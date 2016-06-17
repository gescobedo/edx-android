package edx.android.galileo.gustavo.twitterclient.hashtags.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import edx.android.galileo.gustavo.twitterclient.R;
import edx.android.galileo.gustavo.twitterclient.TwitterClientApp;
import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsPresenter;
import edx.android.galileo.gustavo.twitterclient.hashtags.di.HashtagsComponent;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagsFragment extends Fragment implements HashtagsView, OnItemClickListener {


    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recyclerViewHashtags)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    FrameLayout container;
    @Inject
    HashtagsPresenter presenter;
    @Inject
    HashtagsAdapter adapter;

    public HashtagsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        presenter.getHashtagsTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        HashtagsComponent imagesComponent = app.getHashtagsComponent(this, this);
        imagesComponent.inject(this)    ;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();

    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showHashtags() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Hashtag image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
