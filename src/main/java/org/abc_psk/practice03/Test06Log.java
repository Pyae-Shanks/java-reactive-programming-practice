package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test06Log {

    public static void main(String[] args) {
        var flux = Flux.range(1, 10);

        flux.log("TESTING")
            .subscribe(Util.subscriber());
    }
}
