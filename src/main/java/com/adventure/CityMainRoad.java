package com.adventure;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class CityMainRoad extends Room {
    private int numContents;
    private String[] contents = {"A hole where a sword may fit"};
    private String[] exits = {"South", "West"};

    public CityMainRoad() {
        description = "The main road that leads into the city";
        roomKey = "Road";
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
    public int getNumContents() {
        numContents = hContents.size();
        return numContents;
    }

    @Override
    public int getNextRoom(String direction, HashMap<Integer, Room> rooms) {
        if(direction.equalsIgnoreCase("south")) {
            return getKey(rooms, "Gate");
        } else if(direction.equalsIgnoreCase("west")) {
            return getKey(rooms, "Side");
        }
            else if(direction.equalsIgnoreCase("east")) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean takeItem (String item) {
        return false;
    }

    @Override
    public boolean placeItem(String item) {
        if(item.equalsIgnoreCase("sword")) {
            System.out.println("\nThe wall where the sword was placed has collapsed into the floor");
            hContents.remove(1);
            numContents = hContents.size();
            hExits.put(3, "East");
            setContents(1);
            return true;
        }
        
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