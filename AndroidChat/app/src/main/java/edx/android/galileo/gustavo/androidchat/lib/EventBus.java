package edx.android.galileo.gustavo.androidchat.lib;

/**
 * Created by gustavo on 07/06/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);


}
