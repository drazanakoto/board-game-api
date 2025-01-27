package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.events.GameSessionCreatedEvent;
import fr.razanakoto.dimby.boardgame.domain.session.models.*;
import fr.razanakoto.dimby.boardgame.domain.session.spi.*;
import lombok.AllArgsConstructor;
import lombok.Builder;


@AllArgsConstructor
@Builder
public class GameSessionCreation implements CreateGameSession {

    private final GameSessionInventory gameSessionInventory;
    private final UUIDProvider uuidProvider;
    private final InstantProvider instantProvider;
    private final GameSessionEventProducer gameSessionEventProducer;
    private final PasswordEncoder passwordEncoder;


    @Override
    public GameSession create(Participant participant, String password) {
        var encodedPassword = password == null || password.isEmpty() ? null : passwordEncoder.encode(password);
        var gameSession = GameSession.builder()
                .id(new GameSessionId(uuidProvider.generate()))
                .creator(new GameSessionCreator(participant))
                .status(GameSessionStatus.CREATED)
                .encryptedPassword(encodedPassword)
                .build();
        gameSessionInventory.save(gameSession);
        var sessionWithoutPass = gameSession.withoutPassword();
        var event = new GameSessionCreatedEvent(sessionWithoutPass, instantProvider.now());
        gameSessionEventProducer.publish(event);
        return sessionWithoutPass;
    }
}
