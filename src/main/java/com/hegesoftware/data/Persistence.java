package com.hegesoftware.data;

import com.hegesoftware.models.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Persistence {

    public static boolean saveGame(Player player) {
        try {
            PrintWriter output = new PrintWriter("savegame.txt");
            output.print(player.name + "," + player.balance + "," + player.cheatsEnabled);
            output.close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static Player loadGame() {

        Player player = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"));
            String unparsed = reader.readLine();

            String[] parts = unparsed.split(",");
            String name = parts[0];
            int balance = Integer.parseInt(parts[1]);
            boolean cheatsEnabled = Boolean.parseBoolean(parts[2]);

            player = new Player(name, balance, cheatsEnabled);
        } catch (Exception ignored) {

        }

        return player;
    }
}
