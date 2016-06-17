package edx.android.galileo.gustavo.twitterclient.images.ui;

import java.util.List;

import edx.android.galileo.gustavo.twitterclient.entities.Image;

/**
 * Created by gustavo on 16/06/16.
 */
public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);


}
