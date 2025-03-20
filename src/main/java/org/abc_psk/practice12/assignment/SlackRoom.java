package org.abc_psk.practice12.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class SlackRoom {

    String name;
    Sinks.Many<SlackMessage> sink;
    Flux<SlackMessage> flux;

    public SlackRoom(String name) {
        this.name = name;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    public void addMember(SlackMember member) {
        System.out.println( member.name + " join the room " + name);
        this.subscribe(member);
        member.setMessageConsumer(msg -> this.postMsg(member.name, msg));
    }

    public void subscribe(SlackMember member) {
        this.flux.map(sm -> sm.formatForDelivery(member.name))
                .subscribe(s -> member.receive(s));
    }

    public void postMsg(String sender, String message) {
        this.sink.tryEmitNext(new SlackMessage(sender, message));
    }
}
