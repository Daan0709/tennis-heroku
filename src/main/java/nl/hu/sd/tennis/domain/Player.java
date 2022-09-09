package nl.hu.sd.tennis.domain;

import javax.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int matchesWon = 0;

    private int gamesWon = 0;

    private int setsWon = 0;
    @Enumerated(EnumType.STRING)
    private Score score;

    public Player(String name){
        this.name = name;
    }

    public Player() {
    }

    public void increaseScore(){

        switch (this.score){
            case ZERO:
                this.score = Score.FIFTEEN;
                break;
            case FIFTEEN:
                this.score = Score.THIRTY;
                break;
            case THIRTY:
                this.score = Score.FORTY;
                break;
            case FORTY:
            case ADVANTAGE:
                this.score = Score.ZERO;
                break;
            case DEUCE:
                this.score = Score.ADVANTAGE;
                break;
        }
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Score getScore(){
        return this.score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public int getGamesWon(){
        return gamesWon;
    }

    public void setGamesWon(int games){
        this.gamesWon = games;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void winMatch(){
        this.matchesWon++;
        this.setsWon = 0;
    }

    public void winGame(){
        this.gamesWon++;
    }

    public void winSet(){
        this.gamesWon = 0;
        this.setsWon++;
    }
}
