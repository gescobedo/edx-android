package edx.android.galileo.gustavo.androidchat.login;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import edx.android.galileo.gustavo.androidchat.lib.EventBus;
import edx.android.galileo.gustavo.androidchat.lib.GreenRobotEventBus;
import edx.android.galileo.gustavo.androidchat.login.events.LoginEvent;
import edx.android.galileo.gustavo.androidchat.login.ui.LoginView;

/**
 * Created by gustavo on 07/06/16.
 */
public class LoginPresenterImpl implements LoginPresenter {
   private LoginView loginView;
    private LoginInteractor loginInteractor;
    private EventBus eventBus;

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus= GreenRobotEventBus.getInstance();
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventBus.unregister(this);

    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.doSignUp(email, password);
    }

    private void onSignInSuccess() {
        if (loginView != null) {
            loginView.navigateToMainScreen();
        }
    }

    private void onSignUpSuccess() {
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error) {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgressBar();
            loginView.enableInputs();

        }
    }
}
