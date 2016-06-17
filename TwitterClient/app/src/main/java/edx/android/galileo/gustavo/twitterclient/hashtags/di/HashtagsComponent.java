package edx.android.galileo.gustavo.twitterclient.hashtags.di;

import javax.inject.Singleton;

import dagger.Component;
import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.hashtags.HashtagsPresenter;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.HashtagsFragment;
import edx.android.galileo.gustavo.twitterclient.images.ImagesPresenter;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesFragment;
import edx.android.galileo.gustavo.twitterclient.lib.di.LibsModule;

/**
 * Created by gustavo on 17/06/16.
 */
@Singleton @Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment imagesFragment);
    HashtagsPresenter getPresenter();
}
