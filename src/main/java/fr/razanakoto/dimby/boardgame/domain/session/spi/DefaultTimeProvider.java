package fr.razanakoto.dimby.boardgame.domain.session.spi;

import java.time.Instant;

public class DefaultTimeProvider implements InstantProvider {
    @Override
    public Instant now() {
        return Instant.now();
    }
}
