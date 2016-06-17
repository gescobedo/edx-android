package edx.android.galileo.gustavo.twitterclient.images;

/**
 * Created by gustavo on 16/06/16.
 */
public class ImagesInteractorImpl implements ImagesInteractor {
    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
