package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.spi.InstantProvider;

import java.time.Instant;

public class FakeInstantProvider implements InstantProvider {
    @Override
    public Instant now() {
        return Instant.parse("2025-01-26T11:00:00Z");
    }
}
