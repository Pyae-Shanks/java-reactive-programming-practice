package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test06FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            synchronousSink.next("Hello");
            // synchronousSink.next("World"); // This will cause error.
            // synchronousSink.complete();
        }).take(3).subscribe(Util.subscriber());
    }
}
