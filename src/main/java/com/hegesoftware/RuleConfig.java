package com.hegesoftware;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RuleConfig {

    private static final int gameCost = 1;
    private static final int gamePrize = 3;

    public static int getGameCost() {

        return gameCost;
    }

    public static int getGamePrize() {

        return gamePrize;
    }
}
