package nl.hu.sd.tennis.presentation;

import nl.hu.sd.tennis.application.MatchService;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.exceptions.MatchIsOverException;
import nl.hu.sd.tennis.domain.exceptions.MatchNotFoundException;
import nl.hu.sd.tennis.domain.exceptions.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tennis")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @PostMapping("/match/{id1}/{id2}")
    public Match startMatch(@PathVariable Long id1, @PathVariable Long id2){
        try {
            return this.matchService.startNewMatch(id1, id2);
        } catch (PlayerNotFoundException pnfe){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, pnfe.getMessage());
        }
    }

    @GetMapping("/match")
    public List<Match> findAllMatches(){
        return this.matchService.findAllMatches();
    }

    @GetMapping("/match/{id}")
    public Match getMatch(@PathVariable Long id){
        try {
            return this.matchService.findMatch(id);
        } catch (MatchNotFoundException mnfu){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mnfu.getMessage());
        }
    }

    @PostMapping("/match/point/{playerId}/{matchId}")
    public Match increaseScoreOfPlayer(@PathVariable Long playerId, @PathVariable Long matchId){
        try {
            return this.matchService.increaseScore(matchId, playerId);
        } catch (PlayerNotFoundException | MatchNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        } catch (MatchIsOverException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}
