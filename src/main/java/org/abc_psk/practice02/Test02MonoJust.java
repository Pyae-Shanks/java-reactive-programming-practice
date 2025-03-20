package org.abc_psk.practice02;

import org.abc_psk.practice01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class Test02MonoJust {

    public static void main(String[] args) {

        /*Stream.of(1)
                .peek(i -> System.out.println(i))
                .toList();*/

        Mono<String> mono = Mono.just("Test"); // Publisher
        SubscriberImpl subscriber = new SubscriberImpl(); // Subscriber

        mono.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(3);

        print(mono);

        // MONO Just is to create fast and easy way Publisher.
    }

    public static void print(Publisher<String> message) {
        System.out.println(message);
    }
}
