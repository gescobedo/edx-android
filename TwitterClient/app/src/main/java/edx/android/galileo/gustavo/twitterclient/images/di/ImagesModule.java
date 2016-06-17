package edx.android.galileo.gustavo.twitterclient.images.di;

import android.content.pm.PackageInstaller;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edx.android.galileo.gustavo.twitterclient.api.CustomTwitterApiClient;
import edx.android.galileo.gustavo.twitterclient.entities.Image;
import edx.android.galileo.gustavo.twitterclient.images.ImagesInteractor;
import edx.android.galileo.gustavo.twitterclient.images.ImagesInteractorImpl;
import edx.android.galileo.gustavo.twitterclient.images.ImagesPresenter;
import edx.android.galileo.gustavo.twitterclient.images.ImagesPresenterImpl;
import edx.android.galileo.gustavo.twitterclient.images.ImagesRepository;
import edx.android.galileo.gustavo.twitterclient.images.ImagesRepositoryImpl;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesView;
import edx.android.galileo.gustavo.twitterclient.images.ui.adapters.ImagesAdapter;
import edx.android.galileo.gustavo.twitterclient.images.ui.adapters.OnItemClickListener;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;
import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 17/06/16.
 */
@Module
public class ImagesModule {
    private ImagesView imagesView;
    private OnItemClickListener onItemClickListener;

    public ImagesModule(ImagesView imagesView, OnItemClickListener onItemClickListener) {
        this.imagesView = imagesView;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new ImagesAdapter(onItemClickListener, dataset, imageLoader);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemList() {
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesInteractor interactor, ImagesView view) {
        return new ImagesPresenterImpl(eventBus, interactor, view);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return this.imagesView;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository imagesRepository) {
        return new ImagesInteractorImpl(imagesRepository);
    }

    @Provides
    @Singleton
    ImagesRepository providesRepository(CustomTwitterApiClient customTwitterApiClient, EventBus eventBus) {
        return new ImagesRepositoryImpl(customTwitterApiClient, eventBus);
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
