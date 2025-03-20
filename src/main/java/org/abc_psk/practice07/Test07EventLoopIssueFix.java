package org.abc_psk.practice07;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;

public class Test07EventLoopIssueFix {


    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();

        for (int i = 0; i < 4; i++) {

            client.getProductName(i)
                    .map(Test07EventLoopIssueFix::dummyProcess)
                    .subscribe(Util.subscriber());
        }

        Util.sleep(20);
    }

    public static String dummyProcess (String input) {
        Util.sleep(1);
        return input + " - process";
    }
}
