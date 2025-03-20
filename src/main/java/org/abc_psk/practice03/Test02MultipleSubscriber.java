package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

public class Test02MultipleSubscriber {

    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 );

        flux.subscribe(Util.subscriber("Sub1"));
        flux.filter(i -> i > 7).subscribe(Util.subscriber("Sub2"));
        flux.filter(i -> i % 2 == 0).map(a -> a + "X").subscribe(Util.subscriber("Sub3"));
    }
}
