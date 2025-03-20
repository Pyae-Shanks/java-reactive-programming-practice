package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class TestAssignment03 {

    record PurchaseOrder (String item, String category, Integer price) {}
    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.of(
            "Kids", kidProcessing(),
            "Automotive", automotiveProcessing()
    );

    public static void main(String[] args) {

        orderStream()
                .filter(canProcess())
                .groupBy(po -> po.category)
                .flatMap( gf -> gf.transform(getProcessor(gf.key())))
                .subscribe(Util.subscriber()); // Kids , Automotive

        Util.sleep(50);
    }

    private static PurchaseOrder getOrder () {

        return new PurchaseOrder(
                Util.faker().commerce().productName(),
                Util.faker().commerce().department(),
                Util.faker().random().nextInt(10, 100)
        );
    }

    private static Flux<PurchaseOrder> orderStream () {
        return Flux.interval(Duration.ofMillis(500))
                .map(integer -> getOrder());
    }

    private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing () {
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item, po.category, po.price + 100));
    }

    private static UnaryOperator<Flux<PurchaseOrder>> kidProcessing () {
        return flux -> flux
                .flatMap(po -> getFreeKidsOrder(po).flux().startWith(po));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder (PurchaseOrder order) {
        return Mono.fromSupplier(() -> new PurchaseOrder(order.item + "-FREE", order.category, 0));
    }

    private static Predicate<PurchaseOrder> canProcess() {
        return po -> PROCESSOR_MAP.containsKey(po.category);
    }

    private static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return PROCESSOR_MAP.get(category);
    }
}