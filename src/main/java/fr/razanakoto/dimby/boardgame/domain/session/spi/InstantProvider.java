package fr.razanakoto.dimby.boardgame.domain.session.spi;

import java.time.Instant;

public interface InstantProvider {
    Instant now();
}
