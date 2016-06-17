package edx.android.galileo.gustavo.twitterclient.images;

import android.widget.ImageView;

import org.greenrobot.eventbus.Subscribe;

import edx.android.galileo.gustavo.twitterclient.images.events.ImagesEvent;
import edx.android.galileo.gustavo.twitterclient.images.ui.ImagesView;
import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;

/**
 * Created by gustavo on 16/06/16.
 */
public class ImagesPresenterImpl implements ImagesPresenter {
    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesInteractor interactor, ImagesView view) {
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
    public void getImageTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();

        if (view != null) {
            view.hideProgress();
            view.showImages();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getImages());
            }
        }


    }
}
