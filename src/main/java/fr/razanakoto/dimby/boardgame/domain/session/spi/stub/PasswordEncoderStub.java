package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.spi.PasswordEncoder;

public class PasswordEncoderStub implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return password;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
