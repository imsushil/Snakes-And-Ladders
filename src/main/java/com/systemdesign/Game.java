package com.systemdesign;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Dice dice;
    private Player player1;
    private Player player2;

    public Game() {
        this.board = Board.getInstance();
        this.dice = Dice.getInstance();
    }

    private boolean hasAnyPlayerWon() {
        return this.player1.hasWon() || this.player2.hasWon();
    }

    private Player getWinner() {
        return this.player1.hasWon() ? this.player1 : this.player2;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of player 1: ");
        this.player1 = new Player(sc.nextLine());
        System.out.print("Enter the name of player 2: ");
        this.player2 = new Player(sc.nextLine());
        Player currentPlayer = player1;

        while (!hasAnyPlayerWon()) {
            System.out.print(currentPlayer.getName() + "'s turn: [Press enter to roll the dice]");
            sc.nextLine();

            System.out.println("Rolling the dice...");
            int diceNumber = this.dice.roll();
            System.out.println("Dice shows " + diceNumber + ".");

            int boxNumber = this.board.getBoxNumber(currentPlayer.getScore() + diceNumber);
            if (boxNumber > 0) currentPlayer.setScore(boxNumber);

            System.out.println("You have reached " + currentPlayer.getScore() + ".");

            // Change player's turn
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
        System.out.println("Congratulation! " + this.getWinner().getName() + " has won the match.");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
