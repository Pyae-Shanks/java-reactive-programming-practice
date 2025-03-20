package org.abc_psk.practice03.client;

import org.abc_psk.common.AbstractHttpClient;
import org.abc_psk.practice06.assignment.TestAssignment;
import org.abc_psk.practice13.Test04ContextRateLimiterDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {

    private Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);

    public Flux<String> getNames () {
        // Only call the http request when subscribed.
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getStockPrices () {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }

    public Mono<String> getProductService (int id) {
        return this.httpClient.get()
                .uri("/demo03/product/" + id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getEmptyFallbackProductService (int id) {
        return this.httpClient.get()
                .uri("/demo03/empty-fallback/product/" + id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getTimeoutProductService (int id) {
        return this.httpClient.get()
                .uri("/demo03/timeout-fallback/product/" + id)
                .responseContent()
                .asString()
                .next();
    }

    public Flux<TestAssignment.Order> getOrders () {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map( order -> {
                    String[] orders = order.split(":");
                    return new TestAssignment.Order(orders[0], orders[1], Integer.parseInt(orders[2]), Integer.parseInt(orders[3]));
                })
                .doOnNext(System.out::println)
                .publish()
                .refCount(2);
    }

    public Mono<String> getProductName (int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(product -> log.info("Next -> " + product))
                .publishOn(Schedulers.boundedElastic())
                .next();
    }

    public Mono<String> getPrice(int productId) {
        return this.httpClient.get()
                .uri("/demo05/price/" + productId)
                .responseContent()
                .asString()
                .next();
    }
    public Mono<String> getReview(int productId) {
        return this.httpClient.get()
                .uri("/demo05/review/" + productId)
                .responseContent()
                .asString()
                .next();
    }
    public Mono<String> getProduct(int productId) {
        return this.httpClient.get()
                .uri("/demo05/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getBook() {
        return this.httpClient.get()
                                .uri("/demo07/book")
                                .responseContent()
                                .asString()
                                .startWith(Test04ContextRateLimiterDemo.RateLimiter.limitCalls())
                                .contextWrite(Test04ContextRateLimiterDemo.UserService.userCategoryContext())
                                .next();
    }

}
