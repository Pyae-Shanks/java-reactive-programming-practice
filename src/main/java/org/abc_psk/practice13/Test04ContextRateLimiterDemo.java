package org.abc_psk.practice13;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Test04ContextRateLimiterDemo {

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        for (int i = 0; i < 10; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "sam"))
                    .subscribe(Util.subscriber());
            Util.sleep(1);
        }

        Util.sleep(5);
    }

    public static class UserService {

        private static final Map<String, String> USER_CATEGORY = Map.of(
                "sam", "standard",
                "mike", "prime"
        );

        public static Function<Context, Context> userCategoryContext() {
            return context -> context.<String>getOrEmpty("user")
                    .filter(USER_CATEGORY::containsKey)
                    .map(USER_CATEGORY::get)
                    .map(ctx -> context.put("category", ctx))
                    .orElse(Context.empty());
        }
    }

    public static class RateLimiter {

        private static final Map<String, Integer> categoryAttempts = Collections.synchronizedMap(new HashMap<>());

        static {
            refresh();
        }

        public static <T> Mono<T> limitCalls() {
            return Mono.deferContextual(
                    ctx -> {
                        var allowCall = ctx.<String>getOrEmpty("category")
                                .map(s -> canAllow(s))
                                .orElse(false);

                        return allowCall ? Mono.empty() : Mono.error(new RuntimeException("Rate limit exceeded"));
                    });
        }

        private static boolean canAllow (String category) {
            var attempt = categoryAttempts.getOrDefault(category, 0);
            if (attempt > 0) {
                categoryAttempts.put(category, attempt - 1);
                return true;
            }
            return false;
        }

        private static void refresh() {
            Flux.interval(Duration.ofSeconds(5))
                    .startWith(0L)
                    .subscribe(i -> {
                        categoryAttempts.put("standard", 2);
                        categoryAttempts.put("prime", 3);
                    });
        }
    }
}
