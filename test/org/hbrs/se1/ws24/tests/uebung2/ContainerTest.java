package org.hbrs.se1.ws24.tests.uebung2;

import org.hbrs.se1.ws24.exercises.uebung2.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTest {
    private Container container;
    private ConcreteMember member1;
    private ConcreteMember member2;

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
    void addMember() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void size() {
    }

    @Test
    void dump() {
    }
}