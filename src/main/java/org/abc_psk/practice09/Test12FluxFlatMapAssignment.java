package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice02.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

public class Test12FluxFlatMapAssignment {

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();

        Flux.range(1, 10)
                .flatMap(client::getProductName).subscribe(Util.subscriber());

        Util.sleep(3);
    }
}
