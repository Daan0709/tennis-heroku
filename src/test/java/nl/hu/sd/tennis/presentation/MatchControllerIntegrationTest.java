package nl.hu.sd.tennis.presentation;

import nl.hu.sd.tennis.data.MatchRepository;
import nl.hu.sd.tennis.data.PlayerRepository;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MatchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MatchRepository matchRepository;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("Trying to find a match that doesnt exist should give status code 404")
    void findNonExistingMatchById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/tennis/match/1");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Trying to increase score of a player that doesnt exist in a match should give status code 404")
    void increaseScoreOfNonExistingPlayer() throws Exception {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());
        when(matchRepository.findById(any())).thenReturn(Optional.of(new Match()));
        RequestBuilder request = MockMvcRequestBuilders
                .post("/tennis/match/point/1/2");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Starting a new match should work according to expectations")
    void startNewMatch() throws Exception {
        Player p1 = new Player("Player One");
        Player p2 = new Player("Player Two");
        when(playerRepository.findById(1L)).thenReturn(Optional.of(p1));
        when(playerRepository.findById(2L)).thenReturn(Optional.of(p2));

        RequestBuilder request = MockMvcRequestBuilders
                .post("/tennis/match/1/2");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("PLAYING")))
                .andExpect(jsonPath("$.p1.score", is("ZERO")))
                .andExpect(jsonPath("$.p2.score", is("ZERO")))
                .andExpect(jsonPath("$.p1.name", is("Player One")))
                .andExpect(jsonPath("$.p2.name", is("Player Two")));
    }
}
