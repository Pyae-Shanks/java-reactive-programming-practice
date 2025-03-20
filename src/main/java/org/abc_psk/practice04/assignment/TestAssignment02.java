package org.abc_psk.practice04.assignment;

import org.abc_psk.common.Util;
import org.abc_psk.practice01.subscriber.SubscriberImpl;
import org.abc_psk.practice04.helper.FileReaderServiceImpl;

import java.nio.file.Path;

public class TestAssignment02 {

    public static void main(String[] args) {
        String path = "src/main/java/org/abc_psk/practice04/assignment/";
        FileReaderServiceImpl fri = new FileReaderServiceImpl();
        SubscriberImpl sub = new SubscriberImpl();

        fri.read(Path.of(path+"TestAssignment02.txt")).take(2).subscribe(sub);

        sub.getSubscription().request(1);
        Util.sleep(2);
        sub.getSubscription().request(1);
        Util.sleep(2);
        sub.getSubscription().cancel();
        sub.getSubscription().request(1);
    }

}
