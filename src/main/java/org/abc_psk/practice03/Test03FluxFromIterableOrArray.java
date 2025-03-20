package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Test03FluxFromIterableOrArray {

    public static void main(String[] args) {

        List<String> list = List.of("a", "b", "c");
        Integer[] intArray = {1, 2, 3};

        Flux.fromIterable(list).subscribe(Util.subscriber("List"));

        Flux.fromArray(intArray).subscribe(Util.subscriber("Array"));
    }
}
