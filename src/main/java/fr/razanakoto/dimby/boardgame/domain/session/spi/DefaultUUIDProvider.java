package fr.razanakoto.dimby.boardgame.domain.session.spi;

import java.util.UUID;

public class DefaultUUIDProvider implements UUIDProvider {
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
