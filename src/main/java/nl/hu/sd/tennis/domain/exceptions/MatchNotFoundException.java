package nl.hu.sd.tennis.domain.exceptions;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException(String message){
        super(message);
    }
}
