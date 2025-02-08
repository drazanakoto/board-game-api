package fr.razanakoto.dimby.boardgame.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DefaultPasswordEncoderTest {

    @Test
    void encode() {
        DefaultPasswordEncoder defaultPasswordEncoder = new DefaultPasswordEncoder();
        String password = "password";
        String encodedPassword = defaultPasswordEncoder.encode(password);
        assertTrue(defaultPasswordEncoder.matches(password, encodedPassword));
    }
}