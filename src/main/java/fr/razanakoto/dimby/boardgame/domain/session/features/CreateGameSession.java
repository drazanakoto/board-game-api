package fr.razanakoto.dimby.boardgame.domain.session.features;

import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;
import fr.razanakoto.dimby.boardgame.domain.session.models.Participant;

public interface CreateGameSession {
    GameSession create(Participant participant, String password);
}
