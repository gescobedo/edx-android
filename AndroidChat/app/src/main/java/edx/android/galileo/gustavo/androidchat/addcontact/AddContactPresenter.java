package edx.android.galileo.gustavo.androidchat.addcontact;

import edx.android.galileo.gustavo.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by gustavo on 10/06/16.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
