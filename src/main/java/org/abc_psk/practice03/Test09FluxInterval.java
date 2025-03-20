package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test09FluxInterval {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(500))
                .subscribe(System.out::println);

        Util.sleep(5);
    }
}
