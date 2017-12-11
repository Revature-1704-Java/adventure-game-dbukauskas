package com.adventure;

public class Player {
    private boolean hasSword = false;
    private boolean touchedFizzyDrink = false;

    //check to see if player has the sword
    public boolean hasSword() {
        return this.hasSword;
    }

    //checks to see if the player touched the fizzy drink
    public boolean touchedFizzyDrink() {
        return this.touchedFizzyDrink;
    }

    //has the player take the sword
    public void takeSword() {
        this.hasSword = true;
    }

    //has the player place the sword
    public void placeSword() {
        this.hasSword = false;
    }

    //has the player touch the fizzy drink
    public void touchFizzyDrink() {
        this.touchedFizzyDrink = true;
    }

    //creates string for printing of inventory
    public String toString() {
        if(hasSword) {
            return "\nYou have a sword";
        } else {
            return "\nI think I would know if I was carrying somthing";
        }
    }
}