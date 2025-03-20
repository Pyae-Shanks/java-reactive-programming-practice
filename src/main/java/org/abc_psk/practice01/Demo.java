package org.abc_psk.practice01;

import org.abc_psk.practice01.publisher.PublisherImpl;
import org.abc_psk.practice01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        demo4();
    }

    public static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    public static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(5);
        Thread.sleep(Duration.ofSeconds(3));

        subscriber.getSubscription().request(4);
        Thread.sleep(Duration.ofSeconds(3));

        subscriber.getSubscription().request(2);
        Thread.sleep(Duration.ofSeconds(3));
    }

    public static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(5);
        Thread.sleep(Duration.ofSeconds(3));
        subscriber.getSubscription().cancel();

        subscriber.getSubscription().request(4);
    }

    public static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);

        subscriber.getSubscription().request(5);
        Thread.sleep(Duration.ofSeconds(3));

        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(3));

        subscriber.getSubscription().request(2);
    }
}
