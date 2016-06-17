package edx.android.galileo.gustavo.twitterclient.hashtags.ui;

import java.util.List;

import edx.android.galileo.gustavo.twitterclient.entities.Hashtag;

/**
 * Created by gustavo on 17/06/16.
 */
public interface HashtagsView {
    void showHashtags();
    void hideHashtags();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);


}
