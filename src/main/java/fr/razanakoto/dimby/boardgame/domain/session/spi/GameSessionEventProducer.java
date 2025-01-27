package fr.razanakoto.dimby.boardgame.domain.session.spi;

import fr.razanakoto.dimby.boardgame.domain.session.events.GameSessionCreatedEvent;
import fr.razanakoto.dimby.boardgame.domain.session.events.ParticipantGameSessionAddedEvent;

public interface GameSessionEventProducer {
    void publish(GameSessionCreatedEvent event);
    void publish(ParticipantGameSessionAddedEvent event);
}
