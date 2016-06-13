package edx.android.galileo.gustavo.androidchat.addcontact;

/**
 * Created by gustavo on 11/06/16.
 */
public class AddContactInteractorImpl implements AddContactInteractor {
    AddContactRepository repository;

    public AddContactInteractorImpl() {
    repository= new AddContactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addContact(email);
    }
}

