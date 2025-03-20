package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class Test02StartWithUseCase {

    private static final Logger logger = LoggerFactory.getLogger(Test02StartWithUseCase.class);
    private static List<String> dummyRedis = new ArrayList<>();

    public static void main(String[] args) {

        generateNames().take(2).subscribe(Util.subscriber("sam"));
        generateNames().take(3).subscribe(Util.subscriber("mike"));
        generateNames().take(4).subscribe(Util.subscriber("jake"));
    }

    private static Flux<String> generateNames () {
        return Flux.generate(
                sink -> {
                    logger.info("Generating names ... ");
                    Util.sleep(1);
                    String name = Util.faker().name().firstName();
                    dummyRedis.add(name);
                    sink.next(name);
                })
                .startWith(dummyRedis)
                .cast(String.class);
    }
}
