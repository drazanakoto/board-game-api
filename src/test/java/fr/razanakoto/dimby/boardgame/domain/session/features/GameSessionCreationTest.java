package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.models.*;
import fr.razanakoto.dimby.boardgame.domain.session.spi.stub.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameSessionCreationTest {

    @Test
    public void should_create_a_game_session_with_password() {
        var uuidProvider = new UUIDProviderStub();
        var instantProvider = new InstantProviderStub();
        var gameSessionCreation = GameSessionCreation.builder()
                .uuidProvider(uuidProvider)
                .instantProvider(instantProvider)
                .gameSessionInventory(new GameSessionInventoryStub())
                .gameSessionEventProducer(new GameSessionEventProducerStub())
                .passwordEncoder(new PasswordEncoderStub())
                .build();
        var participant = new Participant(new ParticipantId(uuidProvider.generate()), "John");
        var actual = gameSessionCreation.create(participant, "password");
        var expected = GameSession.builder()
                .id(new GameSessionId(uuidProvider.generate()))
                .creator(new GameSessionCreator(participant))
                .status(GameSessionStatus.CREATED)
                .participants(Set.of())
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void should_create_a_game_session_without_password() {
        var uuidProvider = new UUIDProviderStub();
        var instantProvider = new InstantProviderStub();
        var gameSessionCreation = GameSessionCreation.builder()
                .uuidProvider(uuidProvider)
                .instantProvider(instantProvider)
                .gameSessionInventory(new GameSessionInventoryStub())
                .gameSessionEventProducer(new GameSessionEventProducerStub())
                .passwordEncoder(new PasswordEncoderStub())
                .build();
        var participant = new Participant(new ParticipantId(uuidProvider.generate()), "John");
        var actual = gameSessionCreation.create(participant, null);
        var expected = GameSession.builder()
                .id(new GameSessionId(uuidProvider.generate()))
                .creator(new GameSessionCreator(participant))
                .encryptedPassword(null)
                .status(GameSessionStatus.CREATED)
                .participants(Set.of())
                .build();
        assertEquals(expected, actual);
    }
}