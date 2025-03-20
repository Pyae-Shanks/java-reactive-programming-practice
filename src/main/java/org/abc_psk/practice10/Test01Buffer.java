package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test01Buffer {

    public static void main(String[] args) {

        demo2();
        Util.sleep(60);
    }

    public static void demo1 () {
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    public static void demo2 () {
        eventStream()
                .buffer(3) // every 10 items
                .subscribe(Util.subscriber());
    }

    public static void demo3 () {
        eventStream()
                .buffer(Duration.ofMillis(500)) // every 10 items
                .subscribe(Util.subscriber());
    }

    public static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map( event -> "Event-" + event);
    }

}
