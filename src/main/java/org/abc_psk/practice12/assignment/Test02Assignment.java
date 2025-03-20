package org.abc_psk.practice12.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;

public class Test02Assignment {

    public static void main(String[] args) {

        var room = new Room("Room");
        var sam = new Member("Sam");

        room.addMember(sam);

        sam.says("Hello");
    }
}

class Room {

    String roomName;
    Sinks.Many<String> sink;
    Flux<String> flux;

    Room(String roomName) {
        this.roomName = roomName;
        this.sink = Sinks.many().replay().all();
        this.flux = sink.asFlux();
    }

    void addMember(Member member) {
        flux.subscribe(m -> {
            System.out.println(member + " Received message: "+ m);
        });
        member.send = message -> this.postMessage(member.name, message);
    }

    void postMessage(String sender, String message) {
        this.sink.tryEmitNext(sender + " syas : " + message);
    }
}

class Member {
    String name;
    Consumer<String> send;

    Member(String name) { this.name = name; }

    void says (String message) {
        send.accept(message);
    }
}