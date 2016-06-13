package edx.android.galileo.gustavo.androidchat.addcontact.ui;

/**
 * Created by gustavo on 10/06/16.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgressBar();
    void hideProgressBar();

    void contactAdded();
    void contactNotAdded();
}
