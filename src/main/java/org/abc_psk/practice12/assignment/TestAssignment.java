package org.abc_psk.practice12.assignment;


import org.abc_psk.common.Util;

public class TestAssignment {

    public static void main(String[] args) {

        var room = new SlackRoom("Reactor");
        var sam = new SlackMember("Sam");
        var jake = new SlackMember("Jake");
        var mike = new SlackMember("Mike");


        room.addMember(sam);
        room.addMember(jake);

        sam.says("H");
        Util.sleep(5);

        jake.says("I");
        sam.says("L");

        room.addMember(mike);
        mike.says("L");
    }
}
