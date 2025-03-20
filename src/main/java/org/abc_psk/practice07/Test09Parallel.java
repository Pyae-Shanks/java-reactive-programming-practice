package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


public class Test09Parallel {

    private static Logger log = LoggerFactory.getLogger(Test09Parallel.class);

    public static void main(String[] args) {

        Flux.range(1, 10)
                .parallel(3)
                .runOn(Schedulers.boundedElastic())
                .map(Test09Parallel::process)
                .sequential()
                .subscribe(Util.subscriber());

        Util.sleep(12);
    }

    public static int process (int i) {
        log.info ("Processing " + i);
        Util.sleep(1);
        return i * 2;
    }
}
