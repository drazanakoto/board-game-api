package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.exception.UnknownGameSession;
import fr.razanakoto.dimby.boardgame.domain.session.exception.WrongGameSessionPassword;
import fr.razanakoto.dimby.boardgame.domain.session.exception.WrongGameSessionStatus;
import fr.razanakoto.dimby.boardgame.domain.session.models.*;
import fr.razanakoto.dimby.boardgame.domain.session.spi.stub.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterGameSessionTest {

    @Test
    public void should_throw_exception_when_participant_try_to_join_unknown_game_session_id() {
        var UUIDProvider = new UUIDProviderStub();
        var registerGameSession = RegisterGameSession.builder()
                .gameSessionInventory(new GameSessionInventoryStub())
                .build();
        var participant = new Participant(new ParticipantId(UUIDProvider.generate()), "John");
        var gameSessionId = new GameSessionId(UUIDProvider.generate());
        assertThrows(UnknownGameSession.class, () -> registerGameSession.join(gameSessionId, participant, "password"));
    }

    @Test
    public void should_throw_exception_when_participant_try_to_join_game_session_with_wrong_password() {
        var UUIDProvider = new UUIDProviderStub();
        var gameSessionInventory = new GameSessionInventoryStub();
        gameSessionInventory.save(GameSession.builder()
                .id(new GameSessionId(UUIDProvider.generate()))
                .status(GameSessionStatus.READY)
                .encryptedPassword("password")
                .build()
        );
        var participant = new Participant(new ParticipantId(UUIDProvider.generate()), "John");
        var gameSessionId = new GameSessionId(UUIDProvider.generate());

        var registerGameSession = RegisterGameSession.builder()
                .gameSessionInventory(gameSessionInventory)
                .passwordEncoder(new PasswordEncoderStub())
                .build();
        assertThrows(WrongGameSessionPassword.class, () -> registerGameSession.join(gameSessionId, participant, "wrong_password"));
    }

    @Test
    public void should_throw_exception_when_game_try_to_join_session_with_wrong_status() {
        var UUIDProvider = new UUIDProviderStub();
        var gameSessionInventory = new GameSessionInventoryStub();
        var gameSessionId = new GameSessionId(UUIDProvider.generate());
        var participant = new Participant(new ParticipantId(UUIDProvider.generate()), "John");
        var createdGameSession = GameSession.builder()
                .id(gameSessionId)
                .creator(new GameSessionCreator(participant))
                .status(GameSessionStatus.CREATED)
                .encryptedPassword("password")
                .build();
        gameSessionInventory.save(createdGameSession);

        var registerGameSession = RegisterGameSession.builder()
                .gameSessionInventory(gameSessionInventory)
                .passwordEncoder(new PasswordEncoderStub())
                .gameSessionEventProducer(new GameSessionEventProducerStub())
                .instantProvider(new InstantProviderStub())
                .build();

        assertThrows(WrongGameSessionStatus.class, () -> registerGameSession.join(gameSessionId, participant, "password"));
    }


    @Test
    public void should_register_participant_when_game_session_is_ready() {
        var UUIDProvider = new UUIDProviderStub();
        var gameSessionInventory = new GameSessionInventoryStub();
        var gameSessionId = new GameSessionId(UUIDProvider.generate());
        var participant = new Participant(new ParticipantId(UUIDProvider.generate()), "John");
        var createdGameSession = GameSession.builder()
                .id(gameSessionId)
                .creator(new GameSessionCreator(participant))
                .status(GameSessionStatus.READY)
                .encryptedPassword("password")
                .build();
        gameSessionInventory.save(createdGameSession);

        var registerGameSession = RegisterGameSession.builder()
                .gameSessionInventory(gameSessionInventory)
                .passwordEncoder(new PasswordEncoderStub())
                .gameSessionEventProducer(new GameSessionEventProducerStub())
                .instantProvider(new InstantProviderStub())
                .build();

        var actual = registerGameSession.join(gameSessionId, participant, "password");
        var expected = GameSession.builder()
                .id(createdGameSession.id())
                .creator(createdGameSession.creator())
                .status(GameSessionStatus.READY)
                .participants(Set.of(participant))
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void should_register_participant_when_game_session_without_password_is_ready() {
        var UUIDProvider = new UUIDProviderStub();
        var gameSessionInventory = new GameSessionInventoryStub();
        var gameSessionId = new GameSessionId(UUIDProvider.generate());
        var participant = new Participant(new ParticipantId(UUIDProvider.generate()), "John");
        var createdGameSession = GameSession.builder()
                .id(gameSessionId)
                .creator(new GameSessionCreator(participant))
                .status(GameSessionStatus.READY)
                .build();
        gameSessionInventory.save(createdGameSession);

        var registerGameSession = RegisterGameSession.builder()
                .gameSessionInventory(gameSessionInventory)
                .passwordEncoder(new PasswordEncoderStub())
                .gameSessionEventProducer(new GameSessionEventProducerStub())
                .instantProvider(new InstantProviderStub())
                .build();

        var actual = registerGameSession.join(gameSessionId, participant, null);
        var expected = GameSession.builder()
                .id(createdGameSession.id())
                .creator(createdGameSession.creator())
                .status(GameSessionStatus.READY)
                .participants(Set.of(participant))
                .build();
        assertEquals(expected, actual);
    }
}