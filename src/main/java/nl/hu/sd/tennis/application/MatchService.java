package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.MatchRepository;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.Score;
import nl.hu.sd.tennis.domain.exceptions.MatchNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatchService {
    private MatchRepository matchRepository;
    private PlayerService playerService;

    public MatchService(MatchRepository matchRepository, PlayerService playerService){
        this.matchRepository = matchRepository;
        this.playerService = playerService;
    }

    public Match findMatch(Long id){
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException("There are no matches with id: " + id));
        return match;
    }

    public Match startNewMatch(Long p1id, Long p2id){
        Match match = new Match();
        Player p1 = playerService.findPlayer(p1id);
        Player p2 = playerService.findPlayer(p2id);

        match.start(p1, p2);
        matchRepository.save(match);
        return match;
    }

    public Match increaseScore(Long matchId, Long playerId){
        Match match = findMatch(matchId);
        Player player = playerService.findPlayer(playerId);
        Player otherPlayer;

        if (match.getP1().getId() == playerId){
            otherPlayer = match.getP2();
        } else {
            otherPlayer = match.getP1();
        }

        if (opponentHasAdvantage(otherPlayer)){
            otherPlayer.setScore(Score.DEUCE);
            playerService.savePlayer(otherPlayer);
            return match;
        }

        player.increaseScore();

        if (player.getScore() == Score.FORTY &&
            otherPlayer.getScore() == Score.FORTY){
            player.setScore(Score.DEUCE);
            otherPlayer.setScore(Score.DEUCE);
        }

        if (player.getScore() == Score.ZERO){
            otherPlayer.setScore(Score.ZERO);
        }

        playerService.savePlayer(player);
        playerService.savePlayer(otherPlayer);

        return match;

    }

    public boolean opponentHasAdvantage(Player opponent){
        return opponent.getScore().equals(Score.ADVANTAGE);
    }
}
