package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice09.helper.AirAsia;
import org.abc_psk.practice09.helper.AmericanAirlines;
import org.abc_psk.practice09.helper.NokAir;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test06MergeUseCase {

    private static final Logger log = LoggerFactory.getLogger(Test06MergeUseCase.class);

    public static void main(String[] args) {

        /*Flux.merge(AirAsia.getFlights(), NokAir.getFlights(), AmericanAirlines.getFlights())
                .take(Duration.ofSeconds(2))
                .subscribe(Util.subscriber());*/

        Flux<String> flux = Flux.just("a", "b");
                        flux.startWith(flux).subscribe(Util.subscriber());

        Util.sleep(3);
    }
}
