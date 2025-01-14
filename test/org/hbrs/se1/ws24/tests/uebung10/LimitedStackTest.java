package org.hbrs.se1.ws24.tests.uebung10;

import org.hbrs.se1.ws24.exercises.uebung10.LimitedStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

public class LimitedStackTest {

    private static final int MAX_SIZE = 4;
    private LimitedStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new LimitedStack<>(MAX_SIZE);
    }

    @Test
    public void testEmptyStack() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(EmptyStackException.class, stack::pop);
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    public void testPush() {
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals(1, stack.peek());

        stack.push(2);
        assertEquals(2, stack.size());
        assertEquals(2, stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
        assertEquals(1, stack.peek());

        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testFullStack() {
        for (int i = 1; i <= MAX_SIZE; i++) {
            stack.push(i);
        }
        assertEquals(MAX_SIZE, stack.size());
        assertThrows(IllegalStateException.class, () -> stack.push(5));
    }

    @Test
    public void testPeek() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(2, stack.size()); // Größe bleibt unverändert
    }
}