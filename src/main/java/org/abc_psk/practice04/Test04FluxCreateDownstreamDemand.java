package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import org.abc_psk.practice01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

public class Test04FluxCreateDownstreamDemand {

    public static void main(String[] args) {
        produceOnDemand();
    }

    public static void produceEarly() {
        var subscriber = new SubscriberImpl();
        Flux.<String> create(
                fluxSink -> {
                    for (int i = 0; i < 10; i++) {
                        var name = Util.faker().name().firstName();
                        System.out.println("Generating Name: " + name);
                        fluxSink.next(name);
                    }
                    fluxSink.complete();
                }
        ).subscribe(subscriber);

        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
    }

    public static void produceOnDemand () {
        var subscriber = new SubscriberImpl();
        Flux.<String> create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    System.out.println("Generating Name: " + name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(3);
    }
}
