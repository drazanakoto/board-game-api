package fr.razanakoto.dimby.boardgame.domain.session.models;

import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Builder
public record GameSession(GameSessionId id,
                          GameSessionCreator creator,
                          GameSessionStatus status,
                          Set<Participant> participants,
                          String encryptedPassword) {

    public GameSession {
        participants = participants == null ? new HashSet<>() : participants;
    }

    public boolean needsPassword() {
        return encryptedPassword != null;
    }

    public GameSession withoutPassword() {
        return GameSession.builder()
                .id(id)
                .creator(creator)
                .status(status)
                .participants(new HashSet<>(participants))
                .build();
    }
}
