package org.abc_psk.practice01.test;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class A implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        C c = new C(subscriber); // Subscription
        subscriber.onSubscribe(c);
    }
}
