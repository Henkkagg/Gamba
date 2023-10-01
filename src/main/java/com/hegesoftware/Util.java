package com.hegesoftware;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {

    private static Scanner scanner = null;

    public static Scanner getScanner() {
        if (scanner == null) scanner = new Scanner(System.in);
        return scanner;
    }
}
