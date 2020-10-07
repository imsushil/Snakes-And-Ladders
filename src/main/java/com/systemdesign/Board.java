package com.systemdesign;

import com.systemdesign.exception.InvalidLadderException;
import com.systemdesign.exception.InvalidSnakeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class Board {
    private static Logger logger = LoggerFactory.getLogger(Board.class.getName());

    private static final String INIT_FILE_PATH = "input.txt";
    private static final int ROW = 10;
    private static final int COL = 10;
    private static final int TOTAL_BOXES = ROW * COL;

    private static Board board;

    private HashMap<Integer, Integer> snakesLadders = new HashMap<>();

    private Board() {
        this.initBoard();
    }

    /**
     * This method reads {@value Board#INIT_FILE_PATH} file and adds snakes and ladders on the board.
     */
    private void addSnakesAndLaddersOnBoard() {
        try (InputStream iStream = getClass().getClassLoader().getResourceAsStream(INIT_FILE_PATH);
             BufferedReader br = new BufferedReader(new InputStreamReader(iStream))) {
            String[] inputLine = br.readLine().split(" ");
            int noOfLadders = Integer.parseInt(inputLine[0]);
            int noOfSnakes = Integer.parseInt(inputLine[1]);

            int i = 0;
            while (i++ < noOfLadders) {
                String[] line = br.readLine().split(" ");
                int fromBox = Integer.parseInt(line[0]);
                int toBox = Integer.parseInt(line[1]);
                addLadder(fromBox, toBox);
            }

            i = 0;
            while (i++ < noOfSnakes) {
                String[] line = br.readLine().split(" ");
                int fromBox = Integer.parseInt(line[0]);
                int toBox = Integer.parseInt(line[1]);
                addSnake(fromBox, toBox);
            }

        } catch (IOException io) {
            logger.error("Exception occurred: There is some issue in reading the input file.", io);
        }
    }

    /**
     * This method initialises the board.
     */
    private void initBoard() {
        addSnakesAndLaddersOnBoard();
    }

    /**
     * @param from, box number where the ladder/snake starts
     * @param to,   box number where the ladder/snake ends
     * @return true if the two ends of ladder/snake are on the same row, else false
     */
    private boolean isOnSameRow(int from, int to) {
        return ((from - 1) / 10) == ((to - 1) / 10);
    }

    private boolean isvalidRange(int boxNumber) {
        return boxNumber >= 1 && boxNumber <= TOTAL_BOXES;
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    /**
     * @return total number of boxes i.e row * col => 10 * 10 = 100.
     */
    public int getTotalBoxes() {
        return TOTAL_BOXES;
    }


    /**
     * This method adds a ladder at the given position.
     *
     * @param from, box number where the ladder/snake starts
     * @param to,   box number where the ladder/snake ends
     */
    public void addLadder(int from, int to) {
        if (!isvalidRange(from)) {
            throw new InvalidLadderException("from=" + from + " must be within valid range.");
        }
        if (!isvalidRange(to)) {
            throw new InvalidLadderException("to=" + to + " must be within valid range.");
        }
        if (from >= to) {
            throw new InvalidLadderException("The ladder end(to=" + to + ") must be greater than the ladder start(from=" + from + ")");
        }
        if (isOnSameRow(from, to)) {
            throw new InvalidLadderException("The ladder start(from=" + from + ") & the ladder end(to=" + to + ") must be on different row.");
        }
        snakesLadders.put(from, to);
    }


    /**
     * This method adds a snake at the given position.
     *
     * @param from, box number where the ladder/snake starts
     * @param to,   box number where the ladder/snake ends
     */
    public void addSnake(int from, int to) {
        if (from == TOTAL_BOXES) {
            throw new InvalidSnakeException("Snake cannot be at box number 100.");
        }
        if (!isvalidRange(from)) {
            throw new InvalidSnakeException("from=" + from + " must be within valid range.");
        }
        if (!isvalidRange(to)) {
            throw new InvalidSnakeException("to=" + to + " must be within valid range.");
        }
        if (from <= to) {
            throw new InvalidSnakeException("The snake start(from=" + from + ") must be greater than the snake end(to=" + to + ")");
        }
        if (isOnSameRow(from, to)) {
            throw new InvalidSnakeException("The snake start(from=" + from + ") & the snake end(to=" + to + ") must be on different row.");
        }
        snakesLadders.put(from, to);
    }

    /**
     * This method tells the final box number where the player will reach after the dice is rolled.
     * If there is a ladder/snake at the given boxNumber then it will return the corresponding box number.
     * If the boxNumber is greater than 100, then it will return 0.
     * If there is no ladder/snake at the given boxNumber then it will return the same box number.
     *
     * @param boxNumber, box number after adding the number got on the dice roll
     * @return the corresponding box number at the other end of ladder/snake
     * else 0 if boxNumber is greater than 100
     * else boxNumber if there is no snake/ladder starting from the boxNumber
     */
    public int getBoxNumber(int boxNumber) {
        if (boxNumber > TOTAL_BOXES) return 0;
        else if (this.snakesLadders.containsKey(boxNumber)) {
            return this.snakesLadders.get(boxNumber);
        }
        return boxNumber;
    }

}
