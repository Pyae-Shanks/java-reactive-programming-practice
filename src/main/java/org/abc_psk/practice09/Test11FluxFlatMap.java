package org.abc_psk.practice09;

import org.abc_psk.common.Util;
import org.abc_psk.practice09.applications.OrderService;
import org.abc_psk.practice09.applications.UserService;

public class Test11FluxFlatMap {

    public static void main(String[] args) {

        UserService.getAllUsers()
                .map(UserService.User::id)
                .flatMap(id -> OrderService.getUserOrders(id))
                .subscribe(System.out::println);

        Util.sleep(5);
    }
}
