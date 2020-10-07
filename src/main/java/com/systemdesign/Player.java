package com.systemdesign;

public class Player {
    private String name;
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
        this.score = boxNumber;
    }

    public int getScore() {
        return score;
    }
}