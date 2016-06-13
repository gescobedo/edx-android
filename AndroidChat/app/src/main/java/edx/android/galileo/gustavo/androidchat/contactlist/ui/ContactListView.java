package edx.android.galileo.gustavo.androidchat.contactlist.ui;

import edx.android.galileo.gustavo.androidchat.entities.User;

/**
 * Created by gustavo on 08/06/16.
 */
public interface  ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
