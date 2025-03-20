package org.abc_psk.practice01.test;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class C implements Subscription {

    private Subscriber<? super String> subscriber;
    private final Faker faker;
    private boolean isCancelled = false;

    public C(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long l) {
        if (isCancelled) return;

        for(int i = 0; i < l; i++) {
            this.subscriber.onNext(faker.book().title());
        }
    }

    @Override
    public void cancel() {
        System.out.println("Cancelled.");
        isCancelled = true;
    }
}
