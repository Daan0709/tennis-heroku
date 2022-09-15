package nl.hu.sd.tennis.presentation;

import com.jayway.jsonpath.JsonPath;
import nl.hu.sd.tennis.data.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
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
public class PlayerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @DisplayName("trying to find a player that doesnt exist should give status code 404")
    void findNonExistingPlayer() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/tennis/player/1");

        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("creating a new player should work according to expectations")
    void addNewPlayer() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/tennis/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstname\": \"New\", \"lastname\": \"Player\"}");

        MockHttpServletResponse result = mockMvc.perform(request)
                .andReturn()
                .getResponse();

        Integer playerId = JsonPath.read(result.getContentAsString(), "$.id");

        request = MockMvcRequestBuilders
                .get("/tennis/player/"+playerId);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.name", is("New Player")));
    }

    @BeforeEach
    void init(){
        playerRepository.deleteAll();
    }

    @AfterEach
    void deleteTestData(){
        playerRepository.deleteAll();
    }
}
