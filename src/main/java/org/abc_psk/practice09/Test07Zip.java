package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test07Zip {

    private static final Logger log = LoggerFactory.getLogger(Test07Zip.class);
    record Car (String body, String engine, String tires) {}

    public static void main(String[] args) {

        Flux.zip(getBody(), getEngine(), getTires())
                .map(obj -> new Car(obj.getT1(), obj.getT2(), obj.getT3()))
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }

    public static Flux<String> getBody () {
        return Flux.range(1, 5)
                .map( i -> "Body-" + i )
                .delayElements(Duration.ofMillis(100));
    }

    public static Flux<String> getEngine () {
        return Flux.range(1, 3)
                .map( i -> "Engine-" + i )
                .delayElements(Duration.ofMillis(200));
    }

    public static Flux<String> getTires () {
        return Flux.range(1, 10)
                .map( i -> "Tires-" + i )
                .delayElements(Duration.ofMillis(75));
    }
}
