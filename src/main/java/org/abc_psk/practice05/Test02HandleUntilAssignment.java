package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test02HandleUntilAssignment {

    public static void main(String[] args) {
        var flux = Flux.<String>generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
        }).takeUntil(country -> country.equalsIgnoreCase("canada"));

        flux.handle((country, sink) -> {
            sink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                sink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
