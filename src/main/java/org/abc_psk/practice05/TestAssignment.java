package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class TestAssignment {

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        int id = 4;

        Mono<String> mono = client.getProductService(id);

        mono
            .timeout(Duration.ofSeconds(3), client.getTimeoutProductService(id))
            .switchIfEmpty(client.getEmptyFallbackProductService(id))
            .subscribe(Util.subscriber());

        /*Flux.range(1, 4)
                .map(integer -> client.getProductService(integer))
                        .subscribe(Util.subscriber());*/
        Util.sleep(20);
    }
}
