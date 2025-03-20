package org.abc_psk.practice01.test;

public class TestMain {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        var publisher = new A();
        var subscriber = new B();

        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(7);
    }
}
