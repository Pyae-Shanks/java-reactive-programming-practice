package org.abc_psk.practice12.assignment;

public record SlackMessage(String sender, String message) {

    public static final String MESSAGE_FORMAT = "[%s -> %s] : %s";

    public String formatForDelivery (String receiver) {
        return String.format(MESSAGE_FORMAT, sender, receiver, message);
    }
}
