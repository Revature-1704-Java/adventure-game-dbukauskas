package com.adventure;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class CitySideRoad extends Room {
    private int numContents;
    private String[] exits = {"East", "West"};

    public CitySideRoad() {
        description = "A side road of no significance";
        roomKey = "Side";
        int i = 1;
        for(String a : exits ) {
            hExits.put(i++,a);
        }
    }

    @Override
    public int getNumContents() {
        numContents = hContents.size();
        return numContents;
    }

    @Override
    public int getNextRoom(String direction, HashMap<Integer, Room> rooms) {
        if(direction.equalsIgnoreCase("east")) {
            return getKey(rooms, "Road");
        } else if(direction.equalsIgnoreCase("west")) {
            return getKey(rooms, "dEnd");
        }
        return 0;
    }

    @Override
    public boolean takeItem (String item) {
        return false;
    }

    @Override
    public boolean placeItem(String item) {
        return false;
    }

    @Override
    public boolean canTake() {
        return false;
    }

    @Override
    public boolean canPlace() {
        return true;
    }
}