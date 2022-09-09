package nl.hu.sd.tennis.presentation;

import nl.hu.sd.tennis.application.PlayerService;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.exceptions.PlayerNotFoundException;
import nl.hu.sd.tennis.presentation.dto.PlayerRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tennis")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping("/player")
    public List<Player> findAllPlayers(){
        return playerService.findAllPlayers();
    }

    @GetMapping("/player/{id}")
    public Player getPlayer(@PathVariable Long id){
        try {
            return playerService.findPlayer(id);
        } catch (PlayerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/player")
    public Player createPlayer(@RequestBody PlayerRequestBody body){
        return playerService.createPlayer(body.firstname, body.lastname);
    }

    @DeleteMapping("/player/{id}")
    public Player deletePlayer(@PathVariable Long id){
        try {
            return playerService.deletePlayer(id);
        } catch (PlayerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
