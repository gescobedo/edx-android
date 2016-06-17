package edx.android.galileo.gustavo.twitterclient.images.di;

import javax.inject.Singleton;

import dagger.Component;
import edx.android.galileo.gustavo.twitterclient.images.ImagesPresenter;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesFragment;
import edx.android.galileo.gustavo.twitterclient.lib.di.LibsModule;

/**
 * Created by gustavo on 17/06/16.
 */
@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment imagesFragment);
    ImagesPresenter getPresenter();
}
