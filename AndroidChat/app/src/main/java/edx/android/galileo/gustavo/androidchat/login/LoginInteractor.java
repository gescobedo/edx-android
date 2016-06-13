package edx.android.galileo.gustavo.androidchat.login;

/**
 * Created by gustavo on 07/06/16.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
