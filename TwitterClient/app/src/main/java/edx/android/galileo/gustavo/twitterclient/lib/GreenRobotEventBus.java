package edx.android.galileo.gustavo.twitterclient.lib;

import edx.android.galileo.gustavo.twitterclient.lib.base.EventBus;

/**
 * Created by gustavo on 15/06/16.
 */
public class GreenRobotEventBus implements EventBus {
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void subscribe(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unsubscribe(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
