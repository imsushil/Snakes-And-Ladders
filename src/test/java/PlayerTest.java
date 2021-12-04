import com.systemdesign.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setup() {
        this.player = new Player("Sushil");
    }

    @Test
    public void hasWonTest() {
        int playerScore = 100;
        this.player.setScore(playerScore);
        assertTrue(this.player.hasWon());
    }
}
