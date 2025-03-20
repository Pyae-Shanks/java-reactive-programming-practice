package org.abc_psk.practice11;

import org.abc_psk.common.Util;
import org.abc_psk.practice11.client.ExternalServiceClient;
import org.abc_psk.practice11.client.ServerError;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Test03ExternalServiceDemo {

    public static void main(String[] args) {

        // repeat();
        // retry();

        Util.sleep(60);
    }

    private static void repeat() {
        ExternalServiceClient client = new ExternalServiceClient();

        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        ExternalServiceClient client = new ExternalServiceClient();

        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError () {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(throwable -> ServerError.class.equals(throwable.getClass()))
                .doBeforeRetry(rs -> System.out.println("RETRYING " + rs.failure().getMessage()));
    }

}
