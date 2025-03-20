package org.abc_psk.practice09.assignment;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;
import reactor.core.publisher.Mono;

public class TestAssignment {

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        /*int id = 1;
        Mono.zip(
                client.getProduct(id),
                client.getPrice(id),
                client.getReview(id)
        ).subscribe(Util.subscriber());*/

        for (int i = 0; i < 10; i++) {
            Mono.zip(
                    client.getProduct(i),
                    client.getPrice(i),
                    client.getReview(i)
            ).subscribe(Util.subscriber());
        }

        Util.sleep(3);
    }
}