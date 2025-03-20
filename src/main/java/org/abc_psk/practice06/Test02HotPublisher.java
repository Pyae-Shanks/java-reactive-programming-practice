package org.abc_psk.practice06;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test02HotPublisher {

    public static final Logger log = LoggerFactory.getLogger(Test02HotPublisher.class);


    public static Flux<String> movieStream () {
        return Flux.generate(
                () -> {
                    log.info("Received");
                    return 1;
                },
                (state, sink) -> {
                    String movie = "Scene " + state;
                    log.info(" -------------> Playing " + movie);
                    sink.next(movie);
                    return ++state;
                }
        ).take(10).delayElements(Duration.ofSeconds(1)).cast(String.class);
    }

    public static void main(String[] args) {
        Flux<String> flux = movieStream().share();

        flux.subscribe(Util.subscriber("Sub1"));
        Util.sleep(5);
        flux.subscribe(Util.subscriber("Sub2"));

        Util.sleep(12);

        Flux<String> flux2 = movieStream().publish().refCount(2);
    }
}
