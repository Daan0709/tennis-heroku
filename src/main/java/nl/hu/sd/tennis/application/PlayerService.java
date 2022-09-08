package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.PlayerRepository;
import nl.hu.sd.tennis.domain.Match;
import nl.hu.sd.tennis.domain.Player;
import nl.hu.sd.tennis.domain.exceptions.MatchNotFoundException;
import nl.hu.sd.tennis.domain.exceptions.PlayerNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerService {
    private PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player findPlayer(Long id){
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("There are no players with id: " + id));
        return player;
    }

    public void savePlayer(Player player){
        playerRepository.save(player);
    }
}
