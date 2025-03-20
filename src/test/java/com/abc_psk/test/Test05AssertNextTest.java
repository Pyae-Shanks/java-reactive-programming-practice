package com.abc_psk.test;

import org.abc_psk.common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

public class Test05AssertNextTest {

    record Book(int id, String title, String author) {}

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().title(), Util.faker().book().author()));
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
//                .consumeNextWith(book -> Assertions.assertEquals(1, book.id()))
                .assertNext(book -> Assertions.assertEquals(1, book.id()))
                .thenConsumeWhile(book -> Objects.nonNull(book.title()))
                .expectComplete()
                .verify();
    }

    @Test
    public void assertNextTest2() {
        StepVerifier.create(getBooks().collectList())
//                .consumeNextWith(book -> Assertions.assertEquals(1, book.id()))
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }
}
