package org.abc_psk.practice06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private Map<String, Integer> db = new HashMap<>();

    public void consume (TestAssignment.Order order) {

        Integer current = db.getOrDefault(order.category(), 500);
        Integer update = current - order.quantity();
        db.put(order.category(), update);
    }

    public Flux<String> stream () {
        return Flux.interval(Duration.ofSeconds(2)).map(i -> this.db.toString());
    }
}
