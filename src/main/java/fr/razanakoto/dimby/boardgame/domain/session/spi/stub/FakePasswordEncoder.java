package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.spi.PasswordEncoder;

public class FakePasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return "encodedPassword";
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        var encoded = rawPassword.equals("password") ? "encodedPassword" : rawPassword;
        return encoded.equals(encodedPassword);
    }
}
