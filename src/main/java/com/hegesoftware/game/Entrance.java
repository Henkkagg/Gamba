package com.hegesoftware.game;

import com.hegesoftware.Util;
import com.hegesoftware.models.Player;

public class Entrance {

    public static Player getInfo() {

        System.out.println("Welcome to Gamba-casino!");

        StartAction startAction = StartAction.WaitingForInput;
        while (startAction == StartAction.WaitingForInput) {

            System.out.println("Do you want to load a previous save? Type Y/N.");

            String input = Util.getScanner().nextLine();
            input = input.toUpperCase();

            if (input.equals("Y")) startAction = StartAction.StartingNew;
            if (input.equals("N")) startAction = StartAction.Loading;
        }

        //TODO: Actually load a previous previous save
        if (startAction == StartAction.StartingNew) {
            System.out.println("Can't load a save in alpha version, so starting a new game.");
        }

        //TODO: Implement a cheat code based on name
        System.out.println("As a responsible casino, Gamba needs to know your name as a new player:");
        String name = Util.getScanner().nextLine();

        int balance = 10;
        boolean balanceAccepted = false;
        while(!balanceAccepted) {
            try {
                System.out.println("Your pockets are checked for security reasons. Some cash is found. How many euros?:");
                balance = Util.getScanner().nextInt();

                balanceAccepted = true;
            } catch (Exception ignored) {

            }

            //To jump past the current line
            Util.getScanner().nextLine();
        }



        return new Player(name, balance, false);
    }

    private enum StartAction {
        WaitingForInput,
        Loading,
        StartingNew
    }
}
