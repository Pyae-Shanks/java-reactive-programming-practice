package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test01DefaultBehaviour {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.create(sink -> {

            System.out.println("Flux created");
            for (int i = 0; i < 2; i++) {
                sink.next(i);
            }
            sink.complete();
        });

        flux.subscribe(Util.subscriber("ONE -> "));
        flux.subscribe(Util.subscriber("TWO -> "));
    }
}
