package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.helper.NameGenerator;

public class Test07FluxVsList {

    public static void main(String[] args) {

        var list = NameGenerator.getNameList(10);
        list.forEach(System.out::println);

        var flux = NameGenerator.getNameFlux(10);
        flux.subscribe(Util.subscriber());
    }
}
