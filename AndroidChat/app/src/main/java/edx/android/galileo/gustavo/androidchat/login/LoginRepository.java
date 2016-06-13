package edx.android.galileo.gustavo.androidchat.login;

/**
 * Created by gustavo on 07/06/16.
 */
public interface LoginRepository {
    void signIn(String email,String password);
    void signUp(String email,String password);
    void checkSession();
}
