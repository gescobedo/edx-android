package edx.android.galileo.gustavo.twitterclient.images.events;

import java.util.List;

import edx.android.galileo.gustavo.twitterclient.entities.Image;

/**
 * Created by gustavo on 16/06/16.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
