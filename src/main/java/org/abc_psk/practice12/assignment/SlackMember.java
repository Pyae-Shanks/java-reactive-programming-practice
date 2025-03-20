package org.abc_psk.practice12.assignment;

import java.util.function.Consumer;

public class SlackMember {

    String name;
    Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    public void setMessageConsumer(Consumer<String> messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    public void says (String message) {
        messageConsumer.accept(message);
    }

    public void receive(String message) {
        System.out.println("Received: " + message);
    }
}
