package com.adventure;

import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    public StringBuilder sb;
    public String[] contents;
    public String description;
    public String[] exits;
    public String exitString;
    int count;
    public String roomKey;
    public HashMap<Integer, String> hContents = new HashMap<Integer, String>();
    public HashMap<Integer, String> hExits = new HashMap<Integer, String>();

    public Room() {
        
    }

    //returns the appropriate number to move the room array
    public abstract int getNextRoom(String direction, HashMap<Integer, Room> rooms);
        
    //takes an item from the contents array
    public abstract boolean takeItem(String item);
        
    //places an item from inventory into a room
    public abstract boolean placeItem(String item);
        
    //gets the number of items in the contents array
    public abstract int getNumContents();
        
    //Checks if there are objects to be taken from room
    public abstract boolean canTake();
        
    //Checks if the room has space for an object to be placed
    public abstract boolean canPlace();

    //allows contents to be change, more so to empty the content array
    public void setContents(int index) {
        hContents.remove(index);
    }

    //gets contents of room
    public String[] getContents() {
        return contents;
    }

    //gets description of room
    public String getDescription() {
        return description;
    }

    //allows for the exits of a room to be changed
    public void setExits(String exits, int index) {
        this.exits[index] = exits;
    }

    //gets exits of a room
    public String[] getExits() {
        Map<Integer, String> mapTrav = hExits;
        exits = new String[hExits.size()];
        int numExit = 0;
        for(Map.Entry<Integer, String> entry : mapTrav.entrySet()) {
            exits[numExit] = entry.getValue();
            numExit++;
        }
        return exits;
    }

    //get unique roomKeys
    public String getRoomKey() {
        return roomKey;
    }

    public String toStringContent() {
        StringBuilder sbc = new StringBuilder();
        int numContents = hContents.size();
        int contentsCycled = 0; 
        Map<Integer, String> mapTrav = hContents;
        for(Map.Entry<Integer, String> entry : mapTrav.entrySet()) {
            String newContents = entry.getValue();
            if((numContents - contentsCycled) == 2) {
                sbc.append(newContents + " and ");
            } else if((numContents - contentsCycled) > 2) {
                sbc.append(newContents + ", ");
            } else {
                sbc.append(newContents);
            }
            contentsCycled++;
        }
        return sbc.toString();
    }

    //builds string for exits of rooms to be printed
    public String toStringExits() {
        StringBuilder sbc = new StringBuilder();
        int numExits = hExits.size();
        int exitsCycled = 0; 
        Map<Integer, String> mapTrav = hExits;
        for(Map.Entry<Integer, String> entry : mapTrav.entrySet()) {
            String newExit = entry.getValue();
            if((numExits - exitsCycled) == 2) {
                sbc.append(newExit + " and ");
            } else if((numExits - exitsCycled) > 2) {
                sbc.append(newExit + ", ");
            } else {
                sbc.append(newExit);
            }
            exitsCycled++;
        }
        return sbc.toString();
    }

    public int getKey(HashMap<Integer, Room> rooms, String keyWord) {
        Map<Integer, Room> mapTrav = rooms;
        for(Map.Entry<Integer, Room> entry : mapTrav.entrySet()) {
            Room newRoom = entry.getValue();
            if(newRoom.getRoomKey().equals(keyWord))
                return entry.getKey();
        }
        return 0;
    }
}