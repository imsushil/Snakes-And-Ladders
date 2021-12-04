package com.systemdesign.model;

import java.util.Random;

public class Dice {
    private static final int DICE_MAX_NO = 6;
    private static final int DICE_MIN_NO = 1;

    private static final Dice dice = new Dice();
    private Random random;

    private Dice() {
        this.random = new Random();
    }


    public int roll() {
        return DICE_MIN_NO + this.random.nextInt((DICE_MAX_NO - DICE_MIN_NO) + 1);
    }

    public static Dice getInstance() {
        return dice;
    }
}
