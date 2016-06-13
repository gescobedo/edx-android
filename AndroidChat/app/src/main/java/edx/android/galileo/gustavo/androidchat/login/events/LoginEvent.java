package edx.android.galileo.gustavo.androidchat.login.events;

/**
 * Created by gustavo on 07/06/16.
 */
public class LoginEvent {
    public static final int onSignInError=0;
    public static final int onSignUpError=1;
    public static final int onSignInSuccess=2;
    public static final int onSignUpSuccess=3;
    public static final int onFailedToRecoverSession=4;

    private int eventType;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
