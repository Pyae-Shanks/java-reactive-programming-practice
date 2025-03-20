package org.abc_psk.practice05;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class Test10Transform {

    record Customer (int id, String name) {}
    record Product (int id, String name) {}

    public static void main(String[] args) {

        getCustomers()
                .transform(debug())
                .subscribe();
    }

    public static Flux<Customer> getCustomers () {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, "Customer " + i));
    }

    public static Flux<Product> getProducts () {
        return Flux.range(1, 3)
                .map(i -> new Product(i, "Product " + i));
    }

    public static <T> UnaryOperator<Flux<T>> debug () {

        return flux -> flux
                .doOnNext(item -> System.out.println("doOnNext-1: " + item));
    }
}
