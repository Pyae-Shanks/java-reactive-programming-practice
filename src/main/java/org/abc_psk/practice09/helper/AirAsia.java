package org.abc_psk.practice09.helper;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AirAsia {

    private static final String AIRLINE = "AirAsia";

    public static Flux<Flight> getFlights () {

        return Flux.range(1, Util.faker().random().nextInt(1, 3))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(100, 500)))
                .map(flight -> {
                    return new Flight(AIRLINE, flight);
                }).transform(Util.fluxLogger(AIRLINE));
    }
}
