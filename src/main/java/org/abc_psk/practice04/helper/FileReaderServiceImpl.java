package org.abc_psk.practice04.helper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileReaderServiceImpl implements FileReaderService {

    // solution 1
    /* @Override
    public Flux<String> read(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            return Flux.create(fluxSink -> {
                for (String line : lines) {
                    fluxSink.next(line);
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Flux.empty();
    } */

    // Teacher solution
    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                (this::readFile),
                this::closeFile
        );
    }

    public BufferedReader openFile(Path path) throws IOException {
        return Files.newBufferedReader(path);
    }

    public BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            var line = reader.readLine();
            if(Objects.isNull(line)) {
                sink.complete();
            } else {
                sink.next(line);
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
            sink.error(ioException);
        }
        return reader;
    }

    public void closeFile(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
