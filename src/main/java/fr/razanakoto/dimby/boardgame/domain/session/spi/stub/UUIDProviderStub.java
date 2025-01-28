package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.spi.UUIDProvider;

import java.util.UUID;


public class UUIDProviderStub implements UUIDProvider {

    @Override
    public UUID generate() {
        return UUID.fromString("00000000-0000-0000-0000-000000000000");
    }
}
