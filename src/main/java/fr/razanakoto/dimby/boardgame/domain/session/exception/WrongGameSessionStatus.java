package fr.razanakoto.dimby.boardgame.domain.session.exception;

public class WrongGameSessionStatus extends RuntimeException {
    public WrongGameSessionStatus(String message) {
        super(message);
    }
}
