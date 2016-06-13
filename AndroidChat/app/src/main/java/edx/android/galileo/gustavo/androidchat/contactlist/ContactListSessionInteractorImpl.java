package edx.android.galileo.gustavo.androidchat.contactlist;

/**
 * Created by gustavo on 09/06/16.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepository repository;

    public ContactListSessionInteractorImpl() {
        repository=new ContactListRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
    repository.changeConnectionStatus(online);
    }

    @Override
    public void signoff() {
    repository.signoff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }
}
