package org.hbrs.se1.ws24.tests.uebung3;

import org.hbrs.se1.ws24.exercises.uebung3.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung3.Container;
import org.hbrs.se1.ws24.exercises.uebung3.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerTest {
    private Container container;
    private Member member;

    @BeforeEach
    void setUp() {
        container = Container.forTestPurposeOnly();
        member = new ConcreteMember(1);
    }

    @AfterEach
    void tearDown() {
        container = null;
        member = null;
    }

    @Test
    void testPersistenceStrategyIsNull() {
        try {
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
        try {
            container.load();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.NoStrategyIsSet, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
    }

    @Test
    void testPersistenceStrategyIsNotImplemented() {
        container.setMemberPersistenceStrategy(new PersistenceStrategyMongoDB<>());
        try {
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
        try {
            container.load();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ImplementationNotAvailable, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
    }

    @Test
    void testWrongFileLocation() {
        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        persistenceStrategy.setLocation("/");
        container.setMemberPersistenceStrategy(persistenceStrategy);
        try {
            container.store();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
        try {
            container.load();
        } catch (PersistenceException e) {
            assertEquals(PersistenceException.ExceptionType.ConnectionNotAvailable, e.getExceptionTypeType(), "Falscher ExceptionType!");
        }
    }

    @Test
    void testRoundTrip() {
        PersistenceStrategyStream<Member> persistenceStrategy = new PersistenceStrategyStream<>();
        container.setMemberPersistenceStrategy(persistenceStrategy);

        assertEquals(0, container.size(), "Falsche Container Größe!");

        assertDoesNotThrow(() -> container.addMember(member), "Member konnte nicht hinzugefügt werden!");
        assertEquals(1, container.size(), "Falsche Container Größe!");

        assertDoesNotThrow(() -> container.store(), "Die Member Liste konnte nicht gespeichert werden!");
        assertEquals("Member mit der ID 1 wurde entfernt!", container.deleteMember(1), "Member konnte nicht entfernt werden!");

        assertEquals(0, container.size(), "Falsche Container Größe!");

        assertDoesNotThrow(() -> container.load(), "Member Liste konnte nicht geladen werden!");
        assertEquals(1, container.size(), "Falsche Container Größe!");
    }
}