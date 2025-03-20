package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test01FluxCreate {

    public static void main(String[] args) {
        var flux = Flux.create(fluxSink -> {
            String country = "";
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equalsIgnoreCase("canada"));
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber());
    }
}
