package com.adventure;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Game {
    private String[] exits;
    private Parser parser = new Parser();
    private Player player = new Player();
    private int rmNum = 0;
    private boolean finished = false;
    private Room currentRoom;
    private boolean gameOver = false;
    private HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

    //plays the game
    private void play() {
        fillHashMap();
        Map<Integer, Room> mapTrav = rooms;
        for(Map.Entry<Integer, Room> entry : mapTrav.entrySet()) {
            Room newRoom = entry.getValue();        
            if(newRoom.getRoomKey().equals("Gate")) {
                rmNum = entry.getKey();
            }
        }
        System.out.println();
        while(!finished && !gameOver) {
            currentRoom = rooms.get(rmNum);
            exits = currentRoom.getExits();
            printLocMessage(currentRoom);
            Command command = parser.parseString();
            finished = checkCommand(command);
            System.out.println();
        }
    }

    //prints message for location information
    private void printLocMessage(Room cRoom) {
        System.out.println("You are in front of " + currentRoom.description);
        if(cRoom.getNumContents() > 0) {
            System.out.println("There is " + currentRoom.toStringContent());
        }
        System.out.print("You see an exit to the ");
        System.out.println(cRoom.toStringExits());
    }

    //checks if/what command was given
    private boolean checkCommand(Command command) {
        boolean wantToQuit = false;
        CommandWords commandWords = new CommandWords();

        if(command.isUnknown()) {
            System.out.println("\nYou can't do that");
            return false;
        }

        String cmdWord = command.getCommandWord();
        if(cmdWord.equals("help")) {
            System.out.println(commandWords.toString());
        } else if(cmdWord.equalsIgnoreCase("go")) {
            goRoom(command);
        } else if (cmdWord.equalsIgnoreCase("take")) {
            tryTake(command);
        } else if (cmdWord.equalsIgnoreCase("place")) {
            tryPlace(command);
        } else if (cmdWord.equalsIgnoreCase("inventory")) {
            System.out.println(player.toString());
        } else if (cmdWord.equalsIgnoreCase("quit")) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    //attemps to get the next
    private void goRoom(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("\nI'm going to have to pick a direction.");
            return;
        }

        String direction = command.getSecondWord();
        for(String a : exits) {
            if(a.equalsIgnoreCase(direction)) {
                rmNum = currentRoom.getNextRoom(direction, rooms);
                if(rmNum == -1) {
                    rmNum = 0;
                    gameOver = true;
                    toEndgame();
                }
                return;
            }
        }
        System.out.println("\nThat's not going to work");
    }

    //tries to take an item that fills the content slots of a room
    private void tryTake(Command command) {
        if(!currentRoom.canTake()) {
            System.out.println("\nThere is nothing to take.");
            return;
        }

        if(!command.hasSecondWord()) {
            System.out.println("\nI'm going to have to choose something to take.");
            return;
        }

        String item = command.getSecondWord();
        if(currentRoom.takeItem(item)) {
            player.takeSword();
            currentRoom.setContents(1);
            return;
        }

        //sets bad end flag
        if(item.equalsIgnoreCase("fizzy")) {
            player.touchFizzyDrink();
            return;
        }

        System.out.println("\nThat's not going to work.");
    }

    //tries to place an item from the players inventory
    private void tryPlace(Command command) {
        if(!player.hasSword()) {
            System.out.println("\nI have nothing to place");
            return;
        }
        if(!currentRoom.canPlace()) {
            System.out.println("\nWhere am I going to put anything");
            return;
        }
        if(!command.hasSecondWord()) {
            System.out.println("\nI'm going to have to choose something to place.");
            return;
        }

        String item = command.getSecondWord();
        if(currentRoom.placeItem(item)) {
            player.placeSword();
            return;
        }

        System.out.println("\nThat's not going to work.");
    }

    //quits game on command
    private boolean quit(Command command) {
        return true;
    }

    //writes the endgame message on console
    private void toEndgame() {
        if(player.touchedFizzyDrink()) {
            System.out.println("\nYou enter the room that opened before you.");
            System.out.println("Once in the room the floor opens up and you fall into a new room");
            System.out.println("The ceiling closes. Complete darkness.");
            System.out.println("Game Over");
        } else {
            System.out.println("\nYou enter the now accesible room.");
            System.out.println("Upon closer inspection of the room you do not notice anything remarkable.");
            System.out.println("You exit the room and carry on with the rest of your day.");
            System.out.println("Game Over");
        }
    }

    //this function will read all available maps in the rooms folder and instantiate classes for 
    //use in the rooms hashmap
    private void fillHashMap() {
        // File folder = new File("C:\\Users\\lefty\\Core\\TextAdventure\\src\\main\\java\\com\\adventure\\rooms");
        // File[] listOfFiles = folder.listFiles();
        // String className = "";
        // for(int i = 0; i < listOfFiles.length; i++) {
        //     className = listOfFiles[i].getName().replaceFirst("[.][^.]+$", "");     
        //     try{
        //         rooms.put(i, (Room)(Class.forName(className).newInstance()));
        //     } catch(ClassNotFoundException e) {
        //         System.out.println(e);
        //     } catch(InstantiationException e) {
        //         System.out.println(e);
        //     } catch(IllegalAccessException e) {
        //         System.out.println(e);            
        //     }
        // }
        rooms.put(0, new CityDeadEnd());
        rooms.put(1, new CityMainGate());
        rooms.put(2, new CityMainRoad());
        rooms.put(3, new CitySideRoad());
    }
}