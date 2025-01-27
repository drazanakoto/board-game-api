package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.events.GameSessionCreatedEvent;
import fr.razanakoto.dimby.boardgame.domain.session.exception.UnknownGameSession;
import fr.razanakoto.dimby.boardgame.domain.session.exception.WrongGameSessionPassword;
import fr.razanakoto.dimby.boardgame.domain.session.exception.WrongGameSessionStatus;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSessionId;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSessionStatus;
import fr.razanakoto.dimby.boardgame.domain.session.models.Participant;
import fr.razanakoto.dimby.boardgame.domain.session.spi.GameSessionEventProducer;
import fr.razanakoto.dimby.boardgame.domain.session.spi.GameSessionInventory;
import fr.razanakoto.dimby.boardgame.domain.session.spi.InstantProvider;
import fr.razanakoto.dimby.boardgame.domain.session.spi.PasswordEncoder;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
public class RegisterGameSession implements JoinGameSession {

    private final GameSessionInventory gameSessionInventory;
    private final PasswordEncoder passwordEncoder;
    private final GameSessionEventProducer gameSessionEventProducer;
    private final InstantProvider instantProvider;

    @Override
    public GameSession join(GameSessionId gameSessionId, Participant participant, String password) {
        var foundGameSession = findSession(gameSessionId);

        checkGameSessionStatus(foundGameSession);
        checkPassword(foundGameSession, password);

        foundGameSession = foundGameSession.withoutPassword();
        var updatedGameSession = addParticipant(foundGameSession, participant);
        gameSessionInventory.save(updatedGameSession);
        gameSessionEventProducer.publish(new GameSessionCreatedEvent(updatedGameSession, instantProvider.now()));
        return updatedGameSession;
    }

    private GameSession addParticipant(GameSession gameSession, Participant participant) {
        var participants = new HashSet<>(gameSession.participants());
        participants.add(participant);
        return GameSession.builder()
                .id(gameSession.id())
                .creator(gameSession.creator())
                .status(gameSession.status())
                .encryptedPassword(gameSession.encryptedPassword())
                .participants(participants)
                .build();
    }

    private void checkPassword(GameSession gameSession, String password) {
        if (gameSession.needsPassword() && !passwordEncoder.matches(password, gameSession.encryptedPassword())) {
            throw new WrongGameSessionPassword("Wrong password");
        }
    }

    private void checkGameSessionStatus(GameSession gameSession) {
        var allowedStatus = Set.of(GameSessionStatus.CREATED);
        if (allowedStatus.contains(gameSession.status())) {
            throw new WrongGameSessionStatus("Game session is not in the right status");
        }
    }

    private GameSession findSession(GameSessionId gameSessionId) {
        var optionalGameSession = gameSessionInventory.findById(gameSessionId);
        if (optionalGameSession.isEmpty()) {
            throw new UnknownGameSession("Game session not found :" + gameSessionId);
        }
        return optionalGameSession.get();
    }
}
