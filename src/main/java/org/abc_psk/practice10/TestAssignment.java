package org.abc_psk.practice10;

import org.abc_psk.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAssignment {

    record BookOrder (String genre, String title, Integer price) {}
    record RevenueReport (LocalTime time, Map<String, Integer> revenue) {}
    private static final Logger log = LoggerFactory.getLogger(TestAssignment.class);

    public static void main(String[] args) {

        /*bookOrderStream()
                .buffer(Duration.ofSeconds(5))
                .map( bookOrder -> {
                    Integer revenue = 0;
                    for (BookOrder book : bookOrder) {
                        if (book.genre.equalsIgnoreCase("Mythopoeia") ||
                                book.genre.equals("Metafiction") ||
                        book.genre.equals("Horror")) {
                            revenue = revenue + book.price;
                        }
                    }
                    return revenue;
                })
                .subscribe(Util.subscriber());*/

        Set<String> allowedGenres = Set.of("Mythopoeia", "Metafiction", "Horror");
        bookOrderStream()
                .filter(order -> allowedGenres.contains(order.genre))
                .buffer(Duration.ofSeconds(5))
                .map(TestAssignment::generateReport)
                .subscribe(Util.subscriber());


        Util.sleep(60);
    }

    private static Flux<BookOrder> bookOrderStream () {

        return Flux.interval(Duration.ofMillis(200))
                .map( i -> {
                    String genre = Util.faker().book().genre();
                    String title = Util.faker().book().title();
                    Integer price = Util.faker().random().nextInt(10, 90);

                    return new BookOrder(genre, title, price);
                });
    }

    private static RevenueReport generateReport(List<BookOrder> bookOrders) {
        Map<String, Integer> revenue = bookOrders.stream().collect(Collectors.groupingBy(
                BookOrder::genre,
                Collectors.summingInt(BookOrder::price)));

        return new RevenueReport(LocalTime.now(), revenue);
    }
}
