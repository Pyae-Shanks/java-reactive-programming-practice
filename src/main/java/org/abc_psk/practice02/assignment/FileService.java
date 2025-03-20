package org.abc_psk.practice02.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;

public interface FileService {

    Mono<String> read (String fileName) throws IOException;
    Mono<Void> write (String fileName, String content);
    Mono<Void> delete (String fileName);
}
