package com.hegesoftware;

import com.hegesoftware.game.GameLoop;
import com.hegesoftware.game.Entrance;
import com.hegesoftware.models.Player;

public class Main {
    public static void main(String[] args) {

        Player player = Entrance.getInfo();

        GameLoop.startGame(player);
    }
}