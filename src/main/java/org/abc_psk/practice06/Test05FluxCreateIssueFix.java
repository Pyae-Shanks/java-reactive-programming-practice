package org.abc_psk.practice06;

import org.abc_psk.common.Util;
import org.abc_psk.practice04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Test05FluxCreateIssueFix {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        Flux<String> flux = Flux.create(generator).share();

        flux.subscribe(System.out::println);
        flux.subscribe(Util.subscriber("Sam"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}
