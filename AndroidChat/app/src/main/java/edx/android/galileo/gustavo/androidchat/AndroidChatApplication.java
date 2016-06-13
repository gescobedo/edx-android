package edx.android.galileo.gustavo.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by gustavo on 07/06/16.
 */
public class AndroidChatApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }


}
