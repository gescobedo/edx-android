package edx.android.galileo.gustavo.androidchat.chat;

import org.greenrobot.eventbus.Subscribe;

import edx.android.galileo.gustavo.androidchat.chat.events.ChatEvent;
import edx.android.galileo.gustavo.androidchat.chat.ui.ChatView;
import edx.android.galileo.gustavo.androidchat.entities.User;
import edx.android.galileo.gustavo.androidchat.lib.EventBus;
import edx.android.galileo.gustavo.androidchat.lib.GreenRobotEventBus;

/**
 * Created by gustavo on 13/06/16.
 */
public class ChatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private  ChatView chatView;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl( ChatView chatView) {
        this.chatView = chatView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
        this.chatInteractor = new ChatInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        chatInteractor.unsubscribe();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (chatView!=null){
            chatView.onMessageReceived(event.getMessage());
        }
    }
}
