package edx.android.galileo.gustavo.twitterclient.images;

import edx.android.galileo.gustavo.twitterclient.images.events.ImagesEvent;

/**
 * Created by gustavo on 16/06/16.
 */
public interface ImagesPresenter {
    void onResume();
    void onCreate();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
