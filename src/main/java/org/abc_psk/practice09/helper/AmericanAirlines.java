package org.abc_psk.practice09.helper;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlines {

    private static final String AIRLINE = "American Airlines";

    public static Flux<Flight> getFlights () {

        return Flux.range(1, Util.faker().random().nextInt(5, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(400, 1200)))
                .map(flight -> {
                    return new Flight(AIRLINE, flight);
                }).transform(Util.fluxLogger(AIRLINE));
    }
}
