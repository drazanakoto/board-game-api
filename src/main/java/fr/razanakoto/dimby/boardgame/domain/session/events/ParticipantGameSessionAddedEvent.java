package fr.razanakoto.dimby.boardgame.domain.session.events;

import fr.razanakoto.dimby.boardgame.domain.session.models.GameSession;

import java.time.Instant;

public record ParticipantGameSessionAddedEvent(GameSession session, Instant updatedAt) {
}
