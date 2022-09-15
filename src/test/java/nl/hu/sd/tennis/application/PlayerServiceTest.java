package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.PlayerRepository;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest {

    @Test
    @DisplayName("Function should throw an exception if trying to find a player that doesnt exist")
    void testPlayerNotFoundException(){
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        Player p1 = new Player("Test Player");
        when(playerRepository.findById(1L)).thenReturn(Optional.of(p1));
        PlayerService playerService = new PlayerService(playerRepository);

        assertSame(playerService.findPlayer(1L), p1);
        assertThrows(PlayerNotFoundException.class, () -> playerService.findPlayer(2L));
    }

}
