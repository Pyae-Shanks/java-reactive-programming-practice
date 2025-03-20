package org.abc_psk.practice02;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Test08MonoFromFuture {

    public static void main(String[] args) {
        Mono.fromFuture(() -> getName());
//                .subscribe(Util.subscriber());

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Generating Name");
            return Util.faker().name().firstName();
        });
    }
}
