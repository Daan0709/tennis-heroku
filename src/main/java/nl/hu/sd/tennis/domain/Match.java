package nl.hu.sd.tennis.domain;

import javax.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Player p1;
    @ManyToOne
    private Player p2;
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.WAITING;

    public Match(){}

    public void start(Player p1, Player p2){
        this.setStatus(MatchStatus.PLAYING);
        this.p1 = p1;
        this.p2 = p2;
        p1.setScore(Score.ZERO);
        p2.setScore(Score.ZERO);
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public MatchStatus getStatus() {
        return status;
    }
}
