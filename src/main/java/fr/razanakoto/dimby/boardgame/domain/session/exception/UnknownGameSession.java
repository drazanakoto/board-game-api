package fr.razanakoto.dimby.boardgame.domain.session.exception;

public class UnknownGameSession extends RuntimeException {
    public UnknownGameSession(String message) {
        super(message);
    }
}
