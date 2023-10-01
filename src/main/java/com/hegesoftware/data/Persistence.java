package com.hegesoftware.data;

import com.hegesoftware.models.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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

    //TODO add loadGame-method
}
