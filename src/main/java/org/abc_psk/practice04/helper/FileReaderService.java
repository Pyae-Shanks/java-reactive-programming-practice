package org.abc_psk.practice04.helper;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {

    Flux<String> read(Path path);
}
