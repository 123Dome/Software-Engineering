package org.hbrs.se1.ws24.tests.uebung2;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.hbrs.se1.ws24.exercises.uebung2.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    private Container container;
    private Member member1;
    private Member member2;

    @BeforeEach
    void setUp() {
        container = new Container();
        member1 = new ConcreteMember(1);
        member2 = new ConcreteMember(2);
    }

    @AfterEach
    void tearDown() {
        container = null;
        member1 = null;
        member2 = null;
    }

    @Test
    void testAddMemberAndSize() throws ContainerException {
        // Größe testen beim hinzufügen von Objekten
        assertEquals(0, container.size(), "Falsche Größe!");
        container.addMember(member1);
        assertEquals(1, container.size(), "Falsche Größe!");
        container.addMember(member2);
        assertEquals(2, container.size(), "Falsche Größe!");

        // Testen, ob ein bereits hinzugefügter Member eine ContainerException auswirft
        assertThrows(ContainerException.class, () -> container.addMember(member1), "Exception Klasse wird nicht aufgerufen");
        assertThrows(ContainerException.class, () -> container.addMember(member2), "Exception Klasse wird nicht aufgerufen");

    }

    @Test
    void testDeleteMember() throws ContainerException {
        // Testen, ob leere Liste erkannt wird
        assertEquals(0, container.size(), "Falsche Größe!");
        assertEquals("Fehler! Es existieren noch keine Member!", container.deleteMember(1), "Keine Fehlermeldung beim löschen in einer leeren Liste");

        // Testen, ob fehlerhaft leere Liste erkannt wird
        container.addMember(member1);
        assertEquals(1, container.size(), "Falsche Größe!");
        assertNotEquals("Fehler! Es existieren noch keine Member!", container.deleteMember(1), "Fehlerhafte Ausgabe, dass die Liste leer ist, obwohl Liste nicht leer ist");
        assertEquals(0, container.size(), "Falsche Größe!");

        // Testen, ob nicht vorhandende ID erkannt wird
        container.addMember(member1);
        assertEquals(1, container.size(), "Falsche Größe!");
        assertEquals("Fehler! Kein Member hat diese ID!", container.deleteMember(100), "Nicht vorhandende ID wird als vorhanden erkannt");
        assertEquals(1, container.size(), "Falsche Größe!");

        // Testen, ob ordungsgemäß gelöscht wird
        assertEquals("Member mit der ID " + member1.getID() + " wurde entfernt!", container.deleteMember(1), "Falsche Ausgabe beim erfolgreichen Löschen eines Members");
        assertEquals(0, container.size(), "Falsche Größe!");
    }
}