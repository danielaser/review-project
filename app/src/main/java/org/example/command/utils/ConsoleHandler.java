package org.example.command.utils;

import java.util.Scanner;

public class ConsoleHandler implements IHandler{

    private final Scanner scanner ;

    public ConsoleHandler(){
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }

    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }
}