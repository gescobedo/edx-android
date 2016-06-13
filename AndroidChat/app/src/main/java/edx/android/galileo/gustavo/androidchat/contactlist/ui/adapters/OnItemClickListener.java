package edx.android.galileo.gustavo.androidchat.contactlist.ui.adapters;

import edx.android.galileo.gustavo.androidchat.entities.User;

/**
 * Created by gustavo on 09/06/16.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);

}
