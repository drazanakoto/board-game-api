package fr.razanakoto.dimby.boardgame.domain.session.spi.stub;

import fr.razanakoto.dimby.boardgame.domain.session.events.GameSessionCreatedEvent;
import fr.razanakoto.dimby.boardgame.domain.session.events.ParticipantGameSessionAddedEvent;
import fr.razanakoto.dimby.boardgame.domain.session.spi.GameSessionEventProducer;

public class GameSessionEventProducerStub implements GameSessionEventProducer {
    @Override
    public void publish(GameSessionCreatedEvent event) {

    }

    @Override
    public void publish(ParticipantGameSessionAddedEvent event) {

    }
}
