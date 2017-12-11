package com.adventure;

import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class CityDeadEnd extends Room{
    private int numContents;
    private String[] exits = {"East"};

    public CityDeadEnd() {
        description = "A dead end";
        roomKey = "dEnd";
        int i = 1;
        for(String a : exits ) {
            hExits.put(i++,a);
        }
    }

    public  int getNextRoom(String direction, HashMap<Integer, Room> rooms) {
        return getKey(rooms, "Side");
    }

    @Override
    public  boolean takeItem(String item) {
        return false;
    }

    @Override
    public  boolean placeItem(String item) {
        return false;
    }

    @Override
    public  int getNumContents() {
        numContents = hContents.size();
        return numContents;
    } 
    
    @Override
    public boolean canTake() {
        return false;
    }

    @Override
    public boolean canPlace() {
        return false;
    }
}