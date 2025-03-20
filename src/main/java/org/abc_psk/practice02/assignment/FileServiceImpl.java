package org.abc_psk.practice02.assignment;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileServiceImpl implements FileService {
    private final String filePath = "src/main/java/org/abc_psk/practice02/assignment/";

    @Override
    public Mono<String> read(String fileName) throws IOException {
        File myFile = new File(filePath + fileName);
        Scanner myReader = new Scanner(myFile);
        return Mono.just(myReader.next());
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            writingFile(fileName, content);
        });
    }

    public void writingFile(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(filePath + fileName);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> deleteFile(fileName));
    }

    public void deleteFile(String fileName) {
        File myFile = new File(filePath + fileName);
        myFile.delete();
    }
}
