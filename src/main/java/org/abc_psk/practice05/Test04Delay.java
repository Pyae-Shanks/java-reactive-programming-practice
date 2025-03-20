package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Test04Delay {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleep(12);
    }
}
