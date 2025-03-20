package org.abc_psk.practice09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    public record User(int id, String nam) {}

    public static final Map<String, Integer> userTable = Map.of(
            "Sam", 1,
            "Jake", 2,
            "Mike", 3
    );

    public static Flux<User> getAllUsers() {

        return Flux.fromIterable(userTable.entrySet())
                .map(user -> new User(user.getValue(), user.getKey()));
    }

    public static Mono<Integer> getUser(String name) {
        return Mono.fromSupplier(() -> userTable.get(name));
    }
}
