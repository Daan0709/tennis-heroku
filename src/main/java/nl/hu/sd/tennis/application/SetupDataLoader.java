package nl.hu.sd.tennis.application;

import nl.hu.sd.tennis.data.PlayerRepository;
import nl.hu.sd.tennis.domain.Player;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private PlayerRepository playerRepository;

    public SetupDataLoader(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        this.createPlayers();
        Player p1 = new Player("Rafael Nadal");
        Player p2 = new Player("Roger Federer");
        Player p3 = new Player("Serena Williams");
        Player p4 = new Player("Novak Djokovic");

        alreadySetup = true;
    }

    @Transactional
    void createPlayers() {
        Player p1 = new Player("Rafael Nadal");
        Player p2 = new Player("Roger Federer");
        Player p3 = new Player("Serena Williams");
        Player p4 = new Player("Novak Djokovic");

        playerRepository.save(p1);
        playerRepository.save(p2);
        playerRepository.save(p3);
        playerRepository.save(p4);
    }
}
