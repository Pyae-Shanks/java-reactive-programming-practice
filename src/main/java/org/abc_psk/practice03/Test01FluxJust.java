package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test01FluxJust {

    public static void main(String[] args) {
        Flux.just(1,2,3,4,"Sam")
                .subscribe(Util.subscriber());
    }
}
