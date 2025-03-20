package org.abc_psk.practice11.client;

public class ClientError extends RuntimeException {

    public ClientError() {
        super("Bad Request");
    }
}
