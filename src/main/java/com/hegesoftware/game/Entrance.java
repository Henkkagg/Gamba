package com.hegesoftware.game;

import com.hegesoftware.RuleConfig;
import com.hegesoftware.Util;
import com.hegesoftware.data.Persistence;
import com.hegesoftware.models.Player;

public class Entrance {

    public static Player getInfo() {

        System.out.println("Welcome to Gamba-casino!");

        StartAction startAction = StartAction.WaitingForInput;
        while (startAction == StartAction.WaitingForInput) {

            System.out.println("Do you want to load a previous save? Type Y/N.");

            String input = Util.getScanner().nextLine();
            input = input.toUpperCase();

            if (input.equals("N")) startAction = StartAction.StartingNew;
            if (input.equals("Y")) startAction = StartAction.Loading;
        }

        //TODO: Actually load a previous previous save
        if (startAction == StartAction.Loading) {
            Player player = Persistence.loadGame();

            if (player != null) {
                return player;
            }

            System.out.println("Couldn't load the game. Starting a new one.");
        }

        System.out.println("As a responsible casino, Gamba needs to know your name as a new player:");
        String name = Util.getScanner().nextLine();

        Boolean cheatsAreEnabled = checkForCheats(name);

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



        return new Player(name, balance, cheatsAreEnabled);
    }

    private static Boolean checkForCheats(String name) {

        String cheatCode = RuleConfig.getCheatCode();
        String nameEnding = name.substring(Math.max(0, name.length() - cheatCode.length()));

        if (cheatCode.equals(nameEnding)) {
            System.out.println("Oh, you are a Twitch streamer! Our dealer will put " +
                    "even more randomness to the numbers, compared to the usual!");
            return true;
        }

        return false;
    }

    private enum StartAction {
        WaitingForInput,
        Loading,
        StartingNew
    }
}
