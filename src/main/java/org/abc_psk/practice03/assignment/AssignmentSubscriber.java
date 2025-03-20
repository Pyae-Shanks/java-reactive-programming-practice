package org.abc_psk.practice03.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class AssignmentSubscriber implements Subscriber<Integer> {

    private int initialPrize = 1000;
    private int count = 0;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer stockPrice) {
        if (stockPrice < 90 && initialPrize >= stockPrice ) {
            System.out.println("Buying Stock At = " + stockPrice);
            initialPrize = initialPrize - stockPrice;
            count++;
        }

        if (stockPrice > 110 ) {
            System.out.println("Selling Stock At = " + stockPrice);
            initialPrize = ( count * stockPrice ) + initialPrize;
            System.out.println("You earned = " + initialPrize);
            subscription.cancel();
        }

        System.out.println("Stock Price At = " + stockPrice);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Something went wrong = " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
