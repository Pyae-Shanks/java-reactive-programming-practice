package org.abc_psk.practice01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    public static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

    private int count = 0;
    private boolean isCancelled;
    private static final int MAX_ITEMS = 10;

    private final Faker faker;
    private final Subscriber<? super String> subscriber;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long requestItem) {

        if (isCancelled) return;

        if(requestItem > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Exceed than Maximum items."));
            this.isCancelled = true;
            return;
        }

        log.info("Subscriber has requested {} items", requestItem);
        for(int i = 0 ; i < requestItem && count < MAX_ITEMS; i++) {
            this.subscriber.onNext(this.faker.internet().emailAddress());
            count++;
        }

        if(MAX_ITEMS == count) {
            log.info("No more items left to print.");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Cancelled.");
        isCancelled = true;
    }
}
