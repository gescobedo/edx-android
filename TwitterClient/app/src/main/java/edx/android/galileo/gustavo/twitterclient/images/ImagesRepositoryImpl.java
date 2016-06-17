package edx.android.galileo.gustavo.twitterclient.images;

import android.util.Log;

import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edx.android.galileo.gustavo.twitterclient.api.CustomTwitterApiClient;
import edx.android.galileo.gustavo.twitterclient.entities.Image;
import edx.android.galileo.gustavo.twitterclient.images.events.ImagesEvent;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gustavo on 16/06/16.
 */
public class ImagesRepositoryImpl implements ImagesRepository {
    private EventBus eventBus;
    private CustomTwitterApiClient customTwitterApiClient;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImpl(CustomTwitterApiClient customTwitterApiClient, EventBus eventBus) {
        this.customTwitterApiClient = customTwitterApiClient;
        this.eventBus = eventBus;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(List<Tweet> tweets, Response response) {
                List<Image> images = new ArrayList<Image>();
                for (Tweet twt : tweets) {
                    if (containsImages(twt)) {
                        Image tweetModel = new Image();
                        tweetModel.setId(twt.idStr);
                        tweetModel.setFavoriteCount(twt.favoriteCount);

                        String tweetText = twt.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0) {
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);
                        MediaEntity currentPhoto = twt.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageUrl);
                        images.add(tweetModel);
                    }
                }
                Collections.sort(images, new Comparator<Image>() {
                    @Override
                    public int compare(Image lhs, Image rhs) {
                        return rhs.getFavoriteCount() - lhs.getFavoriteCount();
                    }
                });
                post(images);
            }

            @Override
            public void failure(RetrofitError error) {
                post(error.getLocalizedMessage());
            }
        };
        customTwitterApiClient.getTimeLineService().homeTimeLine(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsImages(Tweet tweet) {
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> images) {
        post(images, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Image> items, String error) {
        ImagesEvent imagesEvent = new ImagesEvent();
        imagesEvent.setError(error);
        imagesEvent.setImages(items);
        eventBus.post(imagesEvent);
    }
}
