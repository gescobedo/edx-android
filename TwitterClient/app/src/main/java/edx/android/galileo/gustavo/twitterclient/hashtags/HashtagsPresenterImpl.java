package edx.android.galileo.gustavo.twitterclient.hashtags;

import org.greenrobot.eventbus.Subscribe;

import edx.android.galileo.gustavo.twitterclient.hashtags.events.HashtagsEvent;
import edx.android.galileo.gustavo.twitterclient.hashtags.ui.HashtagsView;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;

/**
 * Created by gustavo on 17/06/16.
 */
public class HashtagsPresenterImpl implements HashtagsPresenter {
    private EventBus eventBus;
    private HashtagsView view;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsInteractor interactor, HashtagsView view) {
        this.eventBus = eventBus;
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onResume() {
        eventBus.subscribe(this);
    }

    @Override
    public void onCreate() {
        eventBus.subscribe(this);
    }

    @Override
    public void onPause() {
        eventBus.unsubscribe(this);
    }

    @Override
    public void onDestroy() {
        view = null;

        eventBus.unsubscribe(this);

    }

    @Override
    public void getHashtagsTweets() {
        if (view != null) {
            view.hideHashtags();
            view.showProgress();
        }
        interactor.execute();
    }



    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        String errorMsg = event.getError();

        if (view != null) {
            view.hideProgress();
            view.showHashtags();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getHashtags());
            }
        }


    }
}
