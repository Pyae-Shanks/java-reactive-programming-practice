package org.abc_psk.practice01.test;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class B implements Subscriber<String> {

    Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String o) {
        System.out.println(o);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed.");
    }

    public Subscription getSubscription() {
        return this.subscription;
    }
}
