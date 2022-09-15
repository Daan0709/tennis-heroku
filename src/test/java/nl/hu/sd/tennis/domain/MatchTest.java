package nl.hu.sd.tennis.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
    private Player p1;
    private Player p2;

    @BeforeEach
    void init(){
        this.p1 = new Player("Test PlayerOne");
        this.p2 = new Player("Test PlayerTwo");
    }

    @Test
    @DisplayName("starting a match should have 2 players with scores of zero")
    void checkingPlayerScoreOnNewMatch(){
        Match match = new Match();
        match.start(p1, p2);

        assertTrue(match.getP1().getScore() == Score.ZERO &&
                    match.getP2().getScore() == Score.ZERO);
    }

    @Test
    @DisplayName("starting a match should change the match status to PLAYING")
    void startingNewMatch(){
        Match match = new Match();

        assertEquals(MatchStatus.WAITING, match.getStatus());

        match.start(p1, p2);

        assertEquals(MatchStatus.PLAYING, match.getStatus());
    }

}
