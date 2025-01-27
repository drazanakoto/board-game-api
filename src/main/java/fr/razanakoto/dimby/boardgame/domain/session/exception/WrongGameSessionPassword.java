package fr.razanakoto.dimby.boardgame.domain.session.exception;

public class WrongGameSessionPassword extends RuntimeException {
    public WrongGameSessionPassword(String message) {
        super(message);
    }
}
