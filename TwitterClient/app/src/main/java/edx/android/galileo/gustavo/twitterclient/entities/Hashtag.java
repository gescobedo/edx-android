package edx.android.galileo.gustavo.twitterclient.entities;

import java.util.List;

/**
 * Created by gustavo on 17/06/16.
 */
public class Hashtag {
    private String id;
    private List<String> hashtags;
    private String tweetText;
    private int favoriteCount;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status/";

    public String getTweetUrl() {
        return BASE_TWEET_URL + this.id;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }
}

