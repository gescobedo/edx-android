package edx.android.galileo.gustavo.androidchat.contactlist;

import edx.android.galileo.gustavo.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by gustavo on 08/06/16.
 */
public interface ContactListPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();
    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
