package org.abc_psk.practice03;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;

public class Test08NonBlockingStreamingMessages {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        client.getNames().subscribe(Util.subscriber());
        Util.sleep(6);
    }
}
