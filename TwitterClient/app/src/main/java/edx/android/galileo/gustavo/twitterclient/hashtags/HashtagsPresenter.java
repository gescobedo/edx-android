package edx.android.galileo.gustavo.twitterclient.hashtags;

import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.hashtags.events.HashtagsEvent;

/**
 * Created by gustavo on 17/06/16.
 */
public interface HashtagsPresenter {
    void onResume();
    void onCreate();
    void onPause();
    void onDestroy();
    void getHashtagsTweets();
    void onEventMainThread(HashtagsEvent event);
}
