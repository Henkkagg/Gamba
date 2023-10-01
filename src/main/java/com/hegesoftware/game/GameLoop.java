package com.hegesoftware.game;

import com.hegesoftware.data.Persistence;
import com.hegesoftware.RuleConfig;
import com.hegesoftware.Util;
import com.hegesoftware.models.Player;

import java.util.Random;

public class GameLoop {

    private static final Random random = new Random();

    private static final int gameCost = RuleConfig.getGameCost();
    private static final int gamePrize = RuleConfig.getGamePrize();

    public static void startGame(Player player) {
        System.out.println("You sit down at a table of Lucky 7. The sign reads: " + gameCost + "€ per round");

        boolean shouldKeepPlaying = true;
        while (shouldKeepPlaying) {

            System.out.println(player.name + ", you have " + player.balance
                    + "€ remaining. Would you like to play? Type Y/N");
            String input = Util.getScanner().nextLine();
            input = input.toUpperCase();

            if (player.balance < gameCost) {
                System.out.println("Just as you start to muster your answer, the dealer notices that you are too poor.");
                System.out.println("The security staff drags you forcefully away from the table.");

                break;
            }

            if (input.equals("Y")) {
                player.balance -= gameCost;

                int[] randomNumbers = getRandomNumbers(player.cheatsEnabled);
                System.out.println("The lucky numbers are: ");
                printRandomNumbers(randomNumbers);

                //Updates player-object directly and congratulate if player won
                handleGamblingResults(player, randomNumbers);
            }
            if (input.equals("N")) {
                System.out.println("Good bye, " + player.name + "!");
                shouldKeepPlaying = false;
            }
        }

        boolean shouldKeepAskingAboutSaving = true;
        while (shouldKeepAskingAboutSaving) {
            System.out.println("Would you like to save the game? Type Y/N:");
            String input = Util.getScanner().nextLine();
            input = input.toUpperCase();

            if (input.equals("Y")) {
                boolean saveWasSuccessful = Persistence.saveGame(player);

                if (saveWasSuccessful) {
                    System.out.println("Game saved successfully.");
                } else {
                    System.out.println("Game couldn't be saved.");
                }

                shouldKeepAskingAboutSaving = false;
            }

            if (input.equals("N")) {
                shouldKeepAskingAboutSaving = false;
            }
        }
    }

    private static void handleGamblingResults(Player player, int[] randomNumbers) {
        int prizeEarnings = 0;

        for (int randomNumber : randomNumbers) {
            if (randomNumber == 7) prizeEarnings += gamePrize;
        }

        if (prizeEarnings > 0) {
            System.out.println("Congratulations, you won " + prizeEarnings + "€!!!");
        }

        player.balance += prizeEarnings;
    }

    private static int[] getRandomNumbers(Boolean cheatsEnabled) {
        //TODO Make functionality for cheating

        int[] randomNumbers = new int[3];
        for (int i = 0; i < 3; i++) {
            randomNumbers[i] = random.nextInt(10) + 1;
        }

        return randomNumbers;
    }

    private static void printRandomNumbers(int[] randomNumbers) {

        for (int randomNumber : randomNumbers) {
            System.out.print(randomNumber + " ");
        }
        System.out.print("\n");
    }
}
