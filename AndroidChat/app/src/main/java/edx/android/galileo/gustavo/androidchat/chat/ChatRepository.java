package edx.android.galileo.gustavo.androidchat.chat;

/**
 * Created by gustavo on 13/06/16.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);
}
