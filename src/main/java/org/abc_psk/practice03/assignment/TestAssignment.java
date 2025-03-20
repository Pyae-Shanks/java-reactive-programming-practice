package org.abc_psk.practice03.assignment;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;

public class TestAssignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        client.getStockPrices()
                .map(Integer::parseInt)
                .subscribe(new AssignmentSubscriber());

        Util.sleep(21);
    }
}
