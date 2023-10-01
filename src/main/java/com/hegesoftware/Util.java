package com.hegesoftware;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {

    private static int[] numArray;

    private static Scanner scanner = null;

    public static Scanner getScanner() {
        if (scanner == null) scanner = new Scanner(System.in);
        return scanner;
    }

    public static int[] getNumArray() {
        if (numArray == null) {
            numArray = new int[10];
            for (int i = 1; i <= 10; i++) {
                numArray[i - 1] = i;
            }
        }

        return numArray;
    }
}
