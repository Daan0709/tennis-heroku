package nl.hu.sd.tennis.domain.exceptions;

public class MatchIsOverException extends RuntimeException{
    public MatchIsOverException(String message){
        super(message);
    }
}
