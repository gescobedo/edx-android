package edx.android.galileo.gustavo.androidchat.login;

import edx.android.galileo.gustavo.androidchat.login.events.LoginEvent;

/**
 * Created by gustavo on 07/06/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email,String password);
    void registerNewUser(String email,String password);
    void onEventMainThread(LoginEvent event);
}
