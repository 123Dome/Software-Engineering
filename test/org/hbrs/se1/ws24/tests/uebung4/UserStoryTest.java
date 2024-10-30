package org.hbrs.se1.ws24.tests.uebung4;

import org.hbrs.se1.ws24.exercises.uebung4.userStory.UserStory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserStoryTest {

    UserStory userStory = new UserStory(1, "Test Titel", "Test Akzeptanz", "Test Projekt", 4, 4, 2, 2);

    @Test
    void testGetterMethods() {
        assertEquals(1, userStory.getID());
        assertEquals("Test Titel", userStory.getTitle());
        assertEquals("Test Akzeptanz", userStory.getAcceptanceCriteria());
        assertEquals("Test Projekt", userStory.getProject());
        assertEquals(2.0, userStory.getPriority());
    }
}