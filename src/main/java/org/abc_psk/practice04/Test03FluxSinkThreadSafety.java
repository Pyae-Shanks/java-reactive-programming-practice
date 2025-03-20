package org.abc_psk.practice04;

import org.abc_psk.common.Util;
import org.abc_psk.practice04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

public class Test03FluxSinkThreadSafety {

    public static void main(String[] args) {

        // test01();
        test02();
    }

    public static void test01() {
        var list = new ArrayList<Integer>();

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleep(3);
        System.out.println("List size: " + list.size());
    }

    public static void test02() {
        var list = new ArrayList<String>();
        NameGenerator generator = new NameGenerator();
        Flux<String> flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleep(3);
        System.out.println("List size: " + list.size());
    }
}
