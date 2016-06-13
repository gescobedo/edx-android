package edx.android.galileo.gustavo.androidchat.contactlist;

/**
 * Created by gustavo on 08/06/16.
 */
public interface ContactListRepository {
    void signoff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void subscribeToContactListEvents();
    void unsubscribeToContactListEvents();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}
