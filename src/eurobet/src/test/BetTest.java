import eurobet.src.main.Bet;
import eurobet.src.main.User;
import eurobet.src.main.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BetTest {
    private Bet bet;
    private User user;
    private Match match;

    @BeforeEach
    public void setUp() {
        user = new User("mika", "mika", "mika", "mika");
        ArrayList<Match> matches = new ArrayList<>();
        match = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusMinutes(5), "1 : 0");
        bet = new Bet(user, matches);
    }

    @Test
    public void testAddTipValid() {
        Match futureMatch = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusMinutes(5), "1 : 0");
        Match betMatch = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusDays(1), "1 : 0");
        bet.addTip(futureMatch, betMatch);
        assertEquals(1, bet.getBets().size());
    }

    @Test
    public void testAddTipInvalid() {
        Match pastMatch = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().minusMinutes(15), "1 : 0");
        Match betMatch = new Match(new ArrayList<>(), "Italy", LocalDateTime.now().plusDays(1), "1 : 0");
        bet.addTip(pastMatch, betMatch);
        assertEquals(0, bet.getBets().size());
    }

    @Test
    public void testAddTipNullMatch() {
        assertThrows(IllegalArgumentException.class, () -> {
            bet.addTip(null, match);
        });
    }

    @Test
    public void testAddTipNullBet() {
        assertThrows(IllegalArgumentException.class, () -> {
            bet.addTip(match, null);
        });
    }
}
