package edx.android.galileo.gustavo.twitterclient.hashtags.events;

import java.util.List;

import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;

/**
 * Created by gustavo on 17/06/16.
 */
public class HashtagsEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
