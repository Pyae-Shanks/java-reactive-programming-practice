package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice09.applications.OrderService;
import org.abc_psk.practice09.applications.PaymentService;
import org.abc_psk.practice09.applications.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Test16Assignment {

    record UserInformation(Integer userId, String username, Integer balance, List<OrderService.Order> orders) {}

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(user -> {
                    return Mono.zip(
                            PaymentService.getUserBalance(user.id()),
                            OrderService.getUserOrders(user.id()).collectList()
                    ).map(obj -> new UserInformation(user.id(), user.nam(), obj.getT1(), obj.getT2()));
                })
                .subscribe(Util.subscriber());

        Util.sleep(3);
    }
}
