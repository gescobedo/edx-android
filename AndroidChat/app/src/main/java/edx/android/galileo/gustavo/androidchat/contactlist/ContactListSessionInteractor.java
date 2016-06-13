package edx.android.galileo.gustavo.androidchat.contactlist;

/**
 * Created by gustavo on 08/06/16.
 */
public interface ContactListSessionInteractor {
    void signoff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
