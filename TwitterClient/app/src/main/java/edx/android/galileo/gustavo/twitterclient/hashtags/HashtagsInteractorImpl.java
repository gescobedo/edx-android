package edx.android.galileo.gustavo.twitterclient.hashtags;

/**
 * Created by gustavo on 17/06/16.
 */
public class HashtagsInteractorImpl implements HashtagsInteractor {
    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }
}
