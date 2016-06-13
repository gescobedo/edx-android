package edx.android.galileo.gustavo.androidchat.contactlist;

/**
 * Created by gustavo on 08/06/16.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);

}
