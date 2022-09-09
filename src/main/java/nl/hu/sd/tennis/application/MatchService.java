package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.MatchRepository;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.MatchStatus;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.Score;
import nl.hu.sd.tennis.domain.exceptions.MatchIsOverException;
import nl.hu.sd.tennis.domain.exceptions.MatchNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Match> findAllMatches(){
        return matchRepository.findAll();
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
        if (match.getStatus().equals(MatchStatus.OVER)){
            throw new MatchIsOverException("The match is over!");
        }
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
            player.winGame();
            otherPlayer.setScore(Score.ZERO);


            // If a player has won at least 6 games with a difference of 2
            if (player.getGamesWon() >= 6 &&
                    (player.getGamesWon() - otherPlayer.getGamesWon()) > 1){

                otherPlayer.setGamesWon(0);
                player.winSet();                // The player wins a set

                // If the player has won 2 sets, the player wins the match
                if (player.getSetsWon() == 2){
                    player.winMatch();
                    match.setStatus(MatchStatus.OVER);
                }
            }
        }

        playerService.savePlayer(player);
        playerService.savePlayer(otherPlayer);

        return match;

    }

    public boolean opponentHasAdvantage(Player opponent){
        return opponent.getScore().equals(Score.ADVANTAGE);
    }
}
