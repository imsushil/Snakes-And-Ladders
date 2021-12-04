import com.systemdesign.model.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DiceTest {
    private static Dice dice;

    @BeforeAll
    public static void setup() {
        dice = Dice.getInstance();
    }

    @Test
    public void rollTest() {
        int MIN_VALUE = 1, MAX_VALUE = 6;
        int diceNumber = dice.roll();
        Assertions.assertTrue( diceNumber >= MIN_VALUE && diceNumber <= MAX_VALUE);
    }
}
