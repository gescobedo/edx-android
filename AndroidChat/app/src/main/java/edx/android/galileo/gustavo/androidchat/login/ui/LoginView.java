package edx.android.galileo.gustavo.androidchat.login.ui;

/**
 * Created by gustavo on 07/06/16.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

}
