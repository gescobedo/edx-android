package edx.android.galileo.gustavo.twitterclient.lib.base;

/**
 * Created by gustavo on 15/06/16.
 */
public interface EventBus {
    void subscribe(Object subscriber);
    void unsubscribe(Object subscriber);
    void post(Object event);
}
