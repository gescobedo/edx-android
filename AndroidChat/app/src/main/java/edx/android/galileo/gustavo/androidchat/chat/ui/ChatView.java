package edx.android.galileo.gustavo.androidchat.chat.ui;

import edx.android.galileo.gustavo.androidchat.entities.ChatMessage;

/**
 * Created by gustavo on 13/06/16.
 */
public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
