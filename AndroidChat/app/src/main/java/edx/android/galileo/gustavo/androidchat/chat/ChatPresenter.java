package edx.android.galileo.gustavo.androidchat.chat;

import edx.android.galileo.gustavo.androidchat.chat.events.ChatEvent;

/**
 * Created by gustavo on 13/06/16.
 */
public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String email);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
