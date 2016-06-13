package edx.android.galileo.gustavo.androidchat.login;

/**
 * Created by gustavo on 07/06/16.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository=new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
loginRepository.checkSession();
    }

    @Override
    public void doSignUp(String email, String password) {
    loginRepository.signUp(email,password);
    }

    @Override
    public void doSignIn(String email, String password) {
    loginRepository.signIn(email,password);
    }
}
