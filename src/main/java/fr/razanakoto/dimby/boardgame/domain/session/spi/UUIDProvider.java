package fr.razanakoto.dimby.boardgame.domain.session.spi;

import java.util.UUID;

public interface UUIDProvider {
    UUID generate();
}
