package fr.razanakoto.dimby.boardgame.domain.session.spi;

public interface PasswordEncoder {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
