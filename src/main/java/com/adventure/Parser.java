package com.adventure;

import java.util.Scanner;

public class Parser {
    private CommandWords commands;
    private Scanner scanner;

    public Parser() {
        commands = new CommandWords();
        scanner = new Scanner(System.in);
    }
   
    public Command parseString() {
        String input;
        String word1 = null;
        String word2 = null;

        System.out.print(">> ");

        input = scanner.nextLine();  

        Scanner tokenizer = new Scanner(input);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }       
    }
}