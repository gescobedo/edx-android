package edx.android.galileo.gustavo.androidchat.addcontact.events;

/**
 * Created by gustavo on 10/06/16.
 */
public class AddContactEvent {
    boolean error=false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
