package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test08GenerateWithState {

    public static void main(String[] args) {

        Flux.generate(
                () -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    if (counter == 10 || country.equalsIgnoreCase("India")) {
                        sink.complete();
                    }
                    sink.next(country);
                    return ++counter;
                }
        ).subscribe(Util.subscriber());
    }
}
