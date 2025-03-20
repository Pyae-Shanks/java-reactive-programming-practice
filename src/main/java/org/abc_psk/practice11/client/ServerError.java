package org.abc_psk.practice11.client;

public class ServerError extends RuntimeException {

    public ServerError() {
        super("Server error");
    }
}
