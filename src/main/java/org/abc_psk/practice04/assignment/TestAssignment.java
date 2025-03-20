package org.abc_psk.practice04.assignment;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class TestAssignment {

    public static void main(String[] args) {

        Flux.<String>generate(synchronousSink -> {
            synchronousSink.next(Util.faker().country().name());
        }).takeUntil(country -> country.equalsIgnoreCase("canada")).subscribe(Util.subscriber());

        Flux.generate(synchronousSink -> {
            var country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }
}
