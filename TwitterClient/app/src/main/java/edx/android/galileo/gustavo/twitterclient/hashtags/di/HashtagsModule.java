package edx.android.galileo.gustavo.twitterclient.hashtags.di;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edx.android.galileo.gustavo.twitterclient.api.CustomTwitterApiClient;
import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.entities.Image;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsInteractor;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsInteractorImpl;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsPresenter;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsPresenterImpl;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsRepository;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsRepositoryImpl;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.HashtagsView;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters.HashtagsAdapter;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters.OnItemClickListener;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;
import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 17/06/16.
 */
@Module
public class HashtagsModule {
    private HashtagsView hashtagsView;
    private OnItemClickListener onItemClickListener;

    public HashtagsModule(HashtagsView hashtagsView, OnItemClickListener onItemClickListener) {
        this.hashtagsView = hashtagsView;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener onItemClickListener) {
        return new HashtagsAdapter(onItemClickListener, dataset);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemList() {
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsInteractor interactor, HashtagsView view) {
        return new HashtagsPresenterImpl(eventBus, interactor, view);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.hashtagsView;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository hashtagsRepository) {
        return new HashtagsInteractorImpl(hashtagsRepository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesRepository(CustomTwitterApiClient customTwitterApiClient, EventBus eventBus) {
        return new HashtagsRepositoryImpl(customTwitterApiClient, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }


}
