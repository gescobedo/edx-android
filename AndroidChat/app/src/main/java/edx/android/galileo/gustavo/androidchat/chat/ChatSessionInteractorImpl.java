package edx.android.galileo.gustavo.androidchat.chat;

/**
 * Created by gustavo on 13/06/16.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {
    ChatRepository repository;

    public ChatSessionInteractorImpl() {
    this.repository=new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
    repository.changeConnectionStatus(online);
    }
}
