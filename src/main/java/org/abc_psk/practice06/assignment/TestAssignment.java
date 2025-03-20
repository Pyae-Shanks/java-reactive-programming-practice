package org.abc_psk.practice06.assignment;

import org.abc_psk.common.Util;
import org.abc_psk.practice03.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

public class TestAssignment {

    public record Order(String name, String category, Integer price, Integer quantity) {}
    private static Map<String, Integer> revenue = new HashMap<>();

    public static void main(String[] args) {

        ExternalServiceClient client = new ExternalServiceClient();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        client.getOrders().subscribe(order -> {revenueService.consume(order); });
        client.getOrders().subscribe(order -> {inventoryService.consume(order); });

        Util.sleep(20);
    }
}
