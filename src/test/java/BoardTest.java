import com.systemdesign.Board;
import com.systemdesign.exception.InvalidLadderException;
import com.systemdesign.exception.InvalidSnakeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setup() {
        this.board = Board.getInstance();
    }

    /* TESTS FOR addLadder(int from, int to) method */

    @Test
    void whenToValueIsNotBetween1And100ThenAddLadderThrowsException() {
        Exception e = assertThrows(InvalidLadderException.class, () -> board.addLadder(10, 105));
        assertEquals("to=105 must be within valid range.", e.getMessage());
    }

    @Test
    void whenFromValueIsNotBetween1And100ThenAddLadderThrowsException() {
        Exception e = assertThrows(InvalidLadderException.class, () -> board.addLadder(-1, 95));
        assertEquals("from=-1 must be within valid range.", e.getMessage());
    }

    @Test
    void whenFromIsGreaterThanToThenAddLadderThrowsException() {
        Exception e = assertThrows(InvalidLadderException.class, () -> board.addLadder(50, 10));
        assertEquals("The ladder end(to=10) must be greater than the ladder start(from=50)", e.getMessage());
    }

    @Test
    void whenFromAndToAreOnSameRowThenAddLadderThrowsException() {
        Exception e = assertThrows(InvalidLadderException.class, () -> board.addLadder(32, 38));
        assertEquals("The ladder start(from=32) & the ladder end(to=38) must be on different row.", e.getMessage());
    }

    @Test
    void whenFromAndToAreValidThenAddLadderAddsTheLadder() {
        assertDoesNotThrow(() -> board.addLadder(30, 59));
    }





    /* TESTS FOR addSnake(int from, int to) method */

    @Test
    void whenFromValueIsNotBetween1And100ThenAddSnakeThrowsException() {
        Exception e = assertThrows(InvalidSnakeException.class, () -> board.addSnake(105, 10));
        assertEquals("from=105 must be within valid range.", e.getMessage());
    }

    @Test
    void whenToValueIsNotBetween1And100ThenAddSnakeThrowsException() {
        Exception e = assertThrows(InvalidSnakeException.class, () -> board.addSnake(50, -1));
        assertEquals("to=-1 must be within valid range.", e.getMessage());
    }

    @Test
    void whenToIsGreaterThanFromThenAddSnakeThrowsException() {
        Exception e = assertThrows(InvalidSnakeException.class, () -> board.addSnake(10, 50));
        assertEquals("The snake start(from=10) must be greater than the snake end(to=50)", e.getMessage());
    }

    @Test
    void whenFromAndToAreOnSameRowThenAddSnakeThrowsException() {
        Exception e = assertThrows(InvalidSnakeException.class, () -> board.addSnake(38, 31));
        assertEquals("The snake start(from=38) & the snake end(to=31) must be on different row.", e.getMessage());
    }

    @Test
    void whenFromAndToAreValidThenAddSnakeAddsTheLadder() {
        assertDoesNotThrow(() -> board.addSnake(78, 32));
    }

    @Test
    void getTotalBoxesTest() {
        Assertions.assertEquals(100, board.getTotalBoxes());
    }

    /* This method tests getBoxNumber() method.
     * It should return 29 because there is ladder (10,29) on the board.
     */

    @Test
    void getBoxNumberTest() {
        Assertions.assertEquals(29, board.getBoxNumber(10));
    }

    /* This method tests getBoxNumber() method.
     * It should return 0 since given number is greater than the board limit(100).
     */
    @Test
    void getBoxNumberShouldReturnZeroTest() {
        Assertions.assertEquals(0, board.getBoxNumber(105));
    }

    /* This method tests getBoxNumber() method.
     * It should return the same number passed as argument because there is no ladder/snake starting from box 45.
     */
    @Test
    void getBoxNumberShouldTheSameNumberWhichIsPassedAsArgumentTest() {
        Assertions.assertEquals(45, board.getBoxNumber(45));
    }
}
