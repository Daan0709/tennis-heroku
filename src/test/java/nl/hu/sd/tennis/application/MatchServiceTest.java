package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.MatchRepository;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.MatchStatus;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.Score;
import nl.hu.sd.tennis.domain.exceptions.MatchIsOverException;
import nl.hu.sd.tennis.domain.exceptions.MatchNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatchServiceTest {

    @Test
    @DisplayName("starting a new match return a match with correct values")
    void startNewMatch(){
        PlayerService playerService = mock(PlayerService.class);
        MatchRepository matchRepository = mock(MatchRepository.class);

        MatchService matchService = new MatchService(matchRepository, playerService);
        when(playerService.findPlayer(1L)).thenReturn(new Player("Test PlayerOne"));
        when(playerService.findPlayer(2L)).thenReturn(new Player("Test PlayerTwo"));

        Match match = matchService.startNewMatch(1L, 2L);

        assertEquals(MatchStatus.PLAYING, match.getStatus());
        assertEquals(Score.ZERO, match.getP1().getScore());
        assertEquals(Score.ZERO, match.getP2().getScore());
    }

    @Test
    @DisplayName("if the opponent has advantage this function should return true")
    void testOpponentAdvantage(){
        PlayerService playerService = mock(PlayerService.class);
        MatchRepository matchRepository = mock(MatchRepository.class);

        MatchService matchService = new MatchService(matchRepository, playerService);

        Player opponent = new Player("Oppo Nent");

        assertFalse(matchService.opponentHasAdvantage(opponent));
        opponent.setScore(Score.ADVANTAGE);
        assertTrue(matchService.opponentHasAdvantage(opponent));
    }

    @Test
    @DisplayName("function should throw an error if trying to increase the score while the match is over")
    void testMatchOverException(){
        PlayerService playerService = mock(PlayerService.class);
        MatchRepository matchRepository = mock(MatchRepository.class);

        MatchService matchService = new MatchService(matchRepository, playerService);
        Player p1 = new Player("Test PlayerOne");
        Player p2 = new Player("Test PlayerTwo");
        when(playerService.findPlayer(1L)).thenReturn(p1);
        when(playerService.findPlayer(2L)).thenReturn(p2);
        Match match = new Match();
        when(matchRepository.findById(any())).thenReturn(Optional.of(match));

        match.start(p1, p2);
        match.setStatus(MatchStatus.OVER);

        assertThrows(MatchIsOverException.class, () -> matchService.increaseScore(1L, 1L));
    }

    @Test
    @DisplayName("function should throw an error if trying to find a match that doesnt exist")
    void testMatchNotFoundException(){
        PlayerService playerService = mock(PlayerService.class);
        MatchRepository matchRepository = mock(MatchRepository.class);

        MatchService matchService = new MatchService(matchRepository, playerService);

        assertThrows(MatchNotFoundException.class, () -> matchService.increaseScore(2L, 1L));
    }
}
