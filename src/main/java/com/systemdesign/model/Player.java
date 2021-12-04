package com.systemdesign.model;

import com.systemdesign.exception.InvalidScoreException;

public class Player {
    private final String name;
    private int score;
    private static final int MAX_SCORE = Board.getInstance().getTotalBoxes();

    public Player(String name) {
        this.name = name;
    }

    public boolean hasWon() {
        return this.score == MAX_SCORE;
    }

    public String getName() {
        return name;
    }

    public void setScore(int boxNumber) {
        if(boxNumber > MAX_SCORE) throw new InvalidScoreException(boxNumber + " is not a valid box number.");
        this.score = boxNumber;
    }

    public int getScore() {
        return score;
    }
}