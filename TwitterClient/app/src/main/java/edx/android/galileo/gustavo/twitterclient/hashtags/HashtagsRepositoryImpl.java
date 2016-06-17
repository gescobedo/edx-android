package edx.android.galileo.gustavo.twitterclient.hashtags;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edx.android.galileo.gustavo.twitterclient.api.CustomTwitterApiClient;
import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;
import edx.android.galileo.gustavo.twitterclient.hashtags.events.HashtagsEvent;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gustavo on 17/06/16.
 */
public class HashtagsRepositoryImpl implements HashtagsRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient customTwitterApiClient;
    private final static int TWEET_COUNT = 100;

    public HashtagsRepositoryImpl(CustomTwitterApiClient customTwitterApiClient, EventBus eventBus) {
        this.customTwitterApiClient = customTwitterApiClient;
        this.eventBus = eventBus;
    }

    @Override
    public void getHashtags() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> tweets) {
                List<Hashtag> hashtags = new ArrayList<Hashtag>();

                for (Tweet twt : tweets.data) {
                    if (containsHashtags(twt)) {
                        Hashtag tweetModel = new Hashtag();
                        tweetModel.setId(twt.idStr);
                        tweetModel.setFavoriteCount(twt.favoriteCount);


                        tweetModel.setTweetText(twt.text);
                        List<String> items = new ArrayList<String>();
                        for (HashtagEntity ht : twt.entities.hashtags) {
                            items.add(ht.text);
                        }
                        tweetModel.setHashtags(items);
                        // MediaEntity currentPhoto = twt.entities.media.get(0);
                        // String HashtagUrl = currentPhoto.mediaUrl;
                        //tweetModel.setHashtags(HashtagUrl);
                        hashtags.add(tweetModel);
                    }
                }
                Collections.sort(hashtags, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag lhs, Hashtag rhs) {
                        return rhs.getFavoriteCount() - lhs.getFavoriteCount();
                    }
                });
                post(hashtags);
            }

            @Override
            public void failure(TwitterException error) {
                post(error.getLocalizedMessage());
            }
        };
        customTwitterApiClient.getTimeLineService().homeTimeLine(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsHashtags(Tweet tweet) {
        return tweet.entities != null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void post(List<Hashtag> Hashtags) {
        post(Hashtags, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Hashtag> items, String error) {
        HashtagsEvent hashtagsEvent = new HashtagsEvent();
        hashtagsEvent.setError(error);
        hashtagsEvent.setHashtags(items);
        eventBus.post(hashtagsEvent);
    }
}
