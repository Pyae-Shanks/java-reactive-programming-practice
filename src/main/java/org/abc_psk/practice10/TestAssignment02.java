package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;

public class TestAssignment02 {

    private static int counter = 0;

    public static void main(String[] args) {

        eventStream()
                .window(Duration.ofSeconds(10))
                .flatMap(flux -> windowSubscriber(flux))
                .subscribe();

        Util.sleep(30);
    }

    public static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                // .take(10)
                .concatWith(Flux.never())
                .map( event -> "Event-" + event);
    }

    public static Mono<Void> windowSubscriber (Flux<String> flux) {
        return flux.doOnComplete(() -> createTextFile(++counter))
                .then();
    }

    public static void createTextFile (int num) {
        try {
            Path path = Path.of("src/main/resources/textfiles/text" + num +".txt");
            String content = "I was created in " + LocalDate.now();
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
