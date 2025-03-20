package org.abc_psk.practice09.helper;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class NokAir {

    private static final String AIRLINE = "NokAir";

    public static Flux<Flight> getFlights () {

        return Flux.range(1, Util.faker().random().nextInt(4, 6))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300, 700)))
                .map(flight -> {
                    return new Flight(AIRLINE, flight);
                }).transform(Util.fluxLogger(AIRLINE));
    }
}
