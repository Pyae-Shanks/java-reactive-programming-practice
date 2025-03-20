package org.abc_psk.practice02.assignment;

import org.abc_psk.common.Util;
import reactor.core.publisher.Mono;

public class TestAssignment {

    public static void main(String[] args) {
        FileServiceImpl fileService = new FileServiceImpl();

         fileService.write("Test", "Hello Test World").subscribe(Util.subscriber());
         Mono.fromCallable(() -> fileService.read("Test")).subscribe(Util.subscriber());
         fileService.delete("Test.txt").subscribe(Util.subscriber());
    }
}
