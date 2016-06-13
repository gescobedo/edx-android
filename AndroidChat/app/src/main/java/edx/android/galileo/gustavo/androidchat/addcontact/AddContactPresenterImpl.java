package edx.android.galileo.gustavo.androidchat.addcontact;

import org.greenrobot.eventbus.Subscribe;

import edx.android.galileo.gustavo.androidchat.addcontact.events.AddContactEvent;
import edx.android.galileo.gustavo.androidchat.addcontact.ui.AddContactView;
import edx.android.galileo.gustavo.androidchat.lib.EventBus;
import edx.android.galileo.gustavo.androidchat.lib.GreenRobotEventBus;

/**
 * Created by gustavo on 11/06/16.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private AddContactView view;
    private EventBus eventBus;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgressBar();
        }
        interactor.execute(email);
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view=null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgressBar();
            view.showInput();
            if (event.isError()) {
                view.contactNotAdded();;
            } else {
                view.contactAdded();
            }
        }
    }
}
