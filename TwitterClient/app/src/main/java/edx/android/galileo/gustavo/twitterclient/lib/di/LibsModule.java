package edx.android.galileo.gustavo.twitterclient.lib.di;


import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edx.android.galileo.gustavo.twitterclient.lib.GlideImageLoader;
import edx.android.galileo.gustavo.twitterclient.lib.GreenRobotEventBus;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;
import edx.android.galileo.gustavo.twitterclient.lib.base.ImageLoader;

/**
 * Created by gustavo on 16/06/16.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }
    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment requestManager){
        return Glide.with(fragment);
    }
    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
