package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice09.applications.OrderService;
import org.abc_psk.practice09.applications.UserService;

public class Test10MonoFlatMapMany {

    public static void main(String[] args) {

        String name = "Sam";

        UserService.getUser(name)
                .flatMapMany(userId -> OrderService.getUserOrders(userId))
                .subscribe(Util.subscriber());

        Util.sleep(2);
    }
}
