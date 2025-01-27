package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;
import fr.razanakoto.dimby.boardgame.domain.session.models.GameSessionId;
import fr.razanakoto.dimby.boardgame.domain.session.models.Participant;

public interface JoinGameSession {
    GameSession join(GameSessionId gameSessionId, Participant participant, String password);
}
