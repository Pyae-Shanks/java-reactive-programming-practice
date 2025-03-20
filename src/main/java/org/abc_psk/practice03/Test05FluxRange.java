package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test05FluxRange {

    public static void main(String[] args) {

        Flux.range(1, 10)
                .map(x -> Util.faker().name().fullName())
                .subscribe(System.out::println);
    }
}
