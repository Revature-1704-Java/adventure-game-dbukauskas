package com.adventure;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

import com.adventure.CommandWords;
import com.adventure.Room;

public class GameTest {
    Room testDeRoom = new CityDeadEnd();
    Room testMrdRoom = new CityMainRoad();
    Room testMGtRoom = new CityMainGate();
    Room testSdRoom = new CitySideRoad();

    HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();

    @Test
    public final void getRoomDetailsTest() {
        String description = testDeRoom.getDescription();
        String roomKey = testDeRoom.getRoomKey();
        assertEquals("A dead end", description);
        assertEquals("dEnd", roomKey);
    }

    @Test
    public final void getKeyTest() {
        rooms.put(1, testDeRoom);
        rooms.put(2, testMGtRoom);
        rooms.put(3, testMrdRoom);
        rooms.put(4, testSdRoom);

        int key = testDeRoom.getKey(rooms, "Side");
        assertEquals(key, 4);
        key = testDeRoom.getKey(rooms, "Gate");
        assertEquals(key, 2);
        key = testDeRoom.getKey(rooms, "dEnd");
        assertEquals(key, 1);
        key = testDeRoom.getKey(rooms, "Road");
        assertEquals(key, 3);
        key = testDeRoom.getKey(rooms, "");
        assertEquals(key, 0);
    }

    @Test
    public final void toStringExitsTest() {
        String testString = testDeRoom.toStringExits();
        assertEquals(testString, "East");
        testString = testMGtRoom.toStringExits();
        assertEquals(testString, "North");
        testString = testMrdRoom.toStringExits();
        assertEquals(testString, "South and West");
        testString = testSdRoom.toStringExits();
        assertEquals(testString, "East and West");
    }

    @Test
    public final void isCommandTest() {
        CommandWords commandWords = new CommandWords();
        boolean isCommandWord = commandWords.isCommand("go");
        assertTrue(isCommandWord);
        isCommandWord = commandWords.isCommand("");
        assertFalse(isCommandWord);
        isCommandWord = commandWords.isCommand("iNvEnTory");
        assertTrue(isCommandWord);
    }
}