package fr.razanakoto.dimby.boardgame.domain.session.spi;

import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSessionId;

import java.util.Optional;

public interface GameSessionInventory {
    void save(GameSession gameSession);
    Optional<GameSession> findById(GameSessionId gameSessionId);
}
