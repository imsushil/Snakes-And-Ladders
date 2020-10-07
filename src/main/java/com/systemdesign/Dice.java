package com.systemdesign;

import java.util.Random;

public class Dice {
    private static final int DICE_MAX_NO = 6;
    private static final int DICE_MIN_NO = 1;

    private static Dice dice;
    private Random random;

    private Dice() {
        this.random = new Random();
    }


    public int roll() {
        return DICE_MIN_NO + this.random.nextInt((DICE_MAX_NO - DICE_MIN_NO) + 1);
    }

    public static Dice getInstance() {
        if (dice == null) {
            dice = new Dice();
        }
        return dice;
    }
}
