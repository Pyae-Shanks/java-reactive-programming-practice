package org.abc_psk.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

    public static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T item) {
        log.info("{} Received: {}", this.name, item);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("{} Error: {}", this.name, throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("{} Completed", this.name);
    }

}
