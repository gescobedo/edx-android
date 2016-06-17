package edx.android.galileo.gustavo.twitterclient;

import
        android.app.Application;
import android.support.v4.app.Fragment;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import edx.android.galileo.gustavo.twitterclient.hashtags.di.DaggerHashtagsComponent;
import edx.android.galileo.gustavo.twitterclient.hashtags.di.HashtagsComponent;
import edx.android.galileo.gustavo.twitterclient.hashtags.di.HashtagsModule;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.HashtagsView;
import edx.android.galileo.gustavo.twitterclient.images.di.DaggerImagesComponent;
import edx.android.galileo.gustavo.twitterclient.images.di.ImagesComponent;
import edx.android.galileo.gustavo.twitterclient.images.di.ImagesModule;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesView;
import edx.android.galileo.gustavo.twitterclient.lib.di.LibsModule;
import io.fabric.sdk.android.Fabric;

/**
 * Created by gustavo on 14/06/16.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView imagesView, edx.android.galileo.gustavo.twitterclient.images.ui.adapters.OnItemClickListener onItemClickListener) {
        return
                DaggerImagesComponent
                        .builder()
                        .libsModule(new LibsModule(fragment))
                        .imagesModule(new ImagesModule(imagesView, onItemClickListener))
                        .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView imagesView, edx.android.galileo.gustavo.twitterclient.hashtags.ui.adapters.OnItemClickListener onItemClickListener) {
        return
                DaggerHashtagsComponent
                        .builder()
                        .libsModule(new LibsModule(null))
                        .hashtagsModule(new HashtagsModule(imagesView, onItemClickListener))
                        .build();
    }
}
