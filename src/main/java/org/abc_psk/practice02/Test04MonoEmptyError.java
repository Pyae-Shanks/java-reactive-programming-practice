package org.abc_psk.practice02;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

public class Test04MonoEmptyError {

    public static void main(String[] args) {
        // getUserName(3).subscribe(Util.subscriber());
        getUserName(3).subscribe(
                sub -> System.out.println("Test " + sub)
        );
    }

    public static Mono<String> getUserName(int userId) {
        return switch (userId){
            case 1 -> Mono.empty();
            case 2 -> Mono.just("Shanks");
            default -> Mono.error(new RuntimeException("Invalid"));
        };
    }
}
