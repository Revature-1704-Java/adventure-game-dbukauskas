package com.adventure;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class CityMainGate extends Room {
    private int numContents;
    private String[] contents = {"An unattended sword", "A fizzy drink"};
    private String[] exits = {"North"};

    public CityMainGate() {
        description = "The main gate that leads to a large city";
        roomKey = "Gate";
        int i = 1;
        for(String a : contents ) {
            hContents.put(i++,a);
        }
        i = 1;
        for(String a : exits ) {
            hExits.put(i++,a);
        }
    }

    @Override
    public int getNextRoom(String direction, HashMap<Integer, Room> rooms) {
        if(direction.equalsIgnoreCase("north")) {
            return getKey(rooms, "Road");
        }

        return 0;
    }

    @Override
    public boolean takeItem (String item) {
        if(item.equalsIgnoreCase("sword")) {
            return true;
        } else if(item.equals("fizzy")) {
            System.out.println("\nYou do not like fizzy drinks");
        }
        return false;
    }

    @Override
    public boolean placeItem(String item) {
        return false;
    }

    @Override
    public int getNumContents() {
        numContents = hContents.size();
        return numContents;
    }

    @Override
    public boolean canTake() {
        return true;
    }

    @Override
    public boolean canPlace() {
        return false;
    }
}