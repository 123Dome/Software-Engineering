package org.hbrs.se1.ws24.exercises.uebung1.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TranslatorFactory {

    public static GermanTranslator createGermanTranslator() {
        GermanTranslator germanTranslator = new GermanTranslator();

        // Creation Date (format: MMM/YYYY)
        LocalDate datum = LocalDate.now();
        germanTranslator.setDate(datum.format(DateTimeFormatter.ofPattern("MMM/yyyy", Locale.GERMAN)));

        return germanTranslator;
    }
}
