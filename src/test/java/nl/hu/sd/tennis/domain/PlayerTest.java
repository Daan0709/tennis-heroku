package nl.hu.sd.tennis.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player p1;

    @BeforeEach
    void init(){
        this.p1 = new Player("Test Player");
    }

    @Test
    @DisplayName("winning a match should increase the players total won matches by one (1)")
    void winMatchForPlayer(){
        p1.winMatch();

        assertEquals(1, p1.getMatchesWon());
    }

    @ParameterizedTest
    @MethodSource("provideSetExamples")
    @DisplayName("winning a set should increase the player's sets won by one or reset it to zero if they were already one one")
    void winSetForPlayer(int setsWon, int expectedSetsWon, int expectedMatchesWon){
        this.p1.setSetsWon(setsWon);
        this.p1.winSet();

        assertEquals(expectedSetsWon, this.p1.getSetsWon());
        assertEquals(expectedMatchesWon, this.p1.getMatchesWon());
    }

    static Stream<Arguments> provideSetExamples(){
        return Stream.of(
                Arguments.of(0, 1, 0),
                Arguments.of(1, 0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideScoreExamples")
    @DisplayName("increasing a players score should work according to tennis rules")
    void increasePlayerScore(Score givenScore, Score expectedScore){
        this.p1.setScore(givenScore);
        this.p1.increaseScore();

        assertEquals(expectedScore, this.p1.getScore());
    }

    static Stream<Arguments> provideScoreExamples(){
        return Stream.of(
                Arguments.of(Score.ZERO, Score.FIFTEEN),
                Arguments.of(Score.FIFTEEN, Score.THIRTY),
                Arguments.of(Score.THIRTY, Score.FORTY),
                Arguments.of(Score.FORTY, Score.ZERO),
                Arguments.of(Score.DEUCE, Score.ADVANTAGE),
                Arguments.of(Score.ADVANTAGE, Score.ZERO)
        );
    }
}
