package org.hbrs.se1.ws24.tests.uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws24.exercises.uebung1.control.TranslatorFactory;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

    @Test
    public void testValidNumbers(){
        GermanTranslator germanTranslator = TranslatorFactory.createGermanTranslator();

        assertEquals("fünf", germanTranslator.translateNumber(5));
    }

    @Test
    public void testLessThanValidNumbers(){
        GermanTranslator germanTranslator = TranslatorFactory.createGermanTranslator();

        assertNotEquals("null", germanTranslator.translateNumber(0));
    }

    @Test
    public void testMoreThanValidNumbers(){
        GermanTranslator germanTranslator = TranslatorFactory.createGermanTranslator();

        assertNotEquals("fünfzehn", germanTranslator.translateNumber(15));
    }
}