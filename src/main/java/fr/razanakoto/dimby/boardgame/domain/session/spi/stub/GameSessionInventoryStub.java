package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSessionId;
import fr.razanakoto.dimby.boardgame.domain.session.spi.GameSessionInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameSessionInventoryStub implements GameSessionInventory {

    private final Map<GameSessionId, GameSession> gameSessions;

    public GameSessionInventoryStub() {
        gameSessions = new HashMap<>();
    }

    @Override
    public void save(GameSession gameSession) {
        gameSessions.put(gameSession.id(), gameSession);
    }

    @Override
    public Optional<GameSession> findById(GameSessionId gameSessionId) {
        return Optional.ofNullable(gameSessions.get(gameSessionId));
    }
}
