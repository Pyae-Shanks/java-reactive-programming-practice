package org.abc_psk.practice01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {

    public static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Subscribed");
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info("Email: {}", email);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
