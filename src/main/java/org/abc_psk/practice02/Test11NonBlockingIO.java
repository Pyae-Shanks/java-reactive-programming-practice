package org.abc_psk.practice02;

import org.abc_psk.common.Util;
import org.abc_psk.practice02.client.ExternalServiceClient;

public class Test11NonBlockingIO {

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        /* client.getProductName(10)
                .subscribe(Util.subscriber()); */

        for (int i = 0; i < 10; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleep(2);
    }
}
