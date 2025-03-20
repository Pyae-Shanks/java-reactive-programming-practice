package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test10FluxMono {

    public static void main(String[] args) {

        var mono = getUserName(2);
        save(Flux.from(mono));

        var flux = Flux.range(1, 10);
        var monoo1 = flux.next();
        monoo1.subscribe(System.out::println);

        Mono.from(flux).subscribe(Util.subscriber());

    }

    public static Mono<String> getUserName(int userId) {
        return switch (userId){
            case 1 -> Mono.empty();
            case 2 -> Mono.just("Shanks");
            default -> Mono.error(new RuntimeException("Invalid"));
        };
    }

    public static void save(Flux<String> name) {
        name.subscribe(System.out::println);
    }
}
