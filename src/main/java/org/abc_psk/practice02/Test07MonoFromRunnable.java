package org.abc_psk.practice02;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Test07MonoFromRunnable {

    public static void main(String[] args) {
        getProductName(2).subscribe(Util.subscriber());
    }

    public static Mono<String> getProductName (int productId) {
        if(productId == 1) {
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notifyProduct());
    }

    public static void notifyProduct() {
        System.out.println("Notifying...");
    }
}
