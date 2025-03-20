package org.abc_psk.practice06;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test04HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(Test04HotPublisherCache.class);

    public static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(1, 30)))
                .delayElements(Duration.ofSeconds(2))
                .doOnNext(price -> log.info("emitting price {}", price))
                .cast(Integer.class);
    }

    public static void main(String[] args) {

        Flux<Integer> stockStream = stockStream().replay(2).autoConnect(0);

        Util.sleep(5);
        System.out.println("Sam joining.");
        stockStream.subscribe(Util.subscriber("Sam"));

        Util.sleep(4);
        stockStream.subscribe(Util.subscriber("Mike"));

        Util.sleep(12);
    }
}
