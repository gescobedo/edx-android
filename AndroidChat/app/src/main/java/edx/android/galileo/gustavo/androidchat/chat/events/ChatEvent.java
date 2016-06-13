package edx.android.galileo.gustavo.androidchat.chat.events;

import edx.android.galileo.gustavo.androidchat.entities.ChatMessage;

/**
 * Created by gustavo on 13/06/16.
 */
public class ChatEvent {
    private ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
