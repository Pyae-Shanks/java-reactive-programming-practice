package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

public class Test15Then {

    private static final Logger log = LoggerFactory.getLogger(Test15Then.class);

    public static void main(String[] args) {

        saveRecords(List.of("Sam", "Mike", "Jake"))
                .then()
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }

    private static Flux<String> saveRecords (List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved " + r)
                .delayElements(Duration.ofMillis(500));
    }
}
