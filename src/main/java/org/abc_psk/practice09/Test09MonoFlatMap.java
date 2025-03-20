package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice09.applications.PaymentService;
import org.abc_psk.practice09.applications.UserService;
import reactor.core.publisher.Mono;

public class Test09MonoFlatMap {

    public static void main(String[] args) {
        String name = "Sam";

        UserService.getUser(name)
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
    }
}
