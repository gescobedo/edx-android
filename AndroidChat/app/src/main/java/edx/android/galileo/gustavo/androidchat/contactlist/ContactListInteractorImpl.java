package edx.android.galileo.gustavo.androidchat.contactlist;

/**
 * Created by gustavo on 09/06/16.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepository repository;

    public ContactListInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void subscribe() {
        repository.subscribeToContactListEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToContactListEvents();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
