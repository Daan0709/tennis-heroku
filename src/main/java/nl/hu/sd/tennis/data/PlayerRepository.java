package nl.hu.sd.tennis.data;

import nl.hu.sd.tennis.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
