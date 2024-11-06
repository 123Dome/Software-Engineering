package org.hbrs.se1.ws24.exercises.projekt;

import java.io.Serializable;

/**
 * Die Klasse UserStory repräsentiert eine User Story in einem Projektmanagement-Kontext.
 * Sie enthält Attribute für ID, Titel, Akzeptanzkriterium, Projekt, sowie mehrere Kennzahlen
 * (Mehrwert, Strafe, Aufwand, Risiko) zur Berechnung einer Priorität. Diese wird zur Sortierung
 * und Bewertung der User Stories verwendet.
 *
 * TODO Hashmap, Kommando Design Pattern, Einzelne Klassen für Kommandos?
 */
public class UserStory implements Serializable {
    private Integer ID;
    private String title;
    private String acceptanceCriteria;
    private String project;

    // Kennzahlen für Priorisierung
    private Integer value; // Mehrwert
    private Integer penalty; // Strafe
    private Integer effort; // Aufwand
    private Integer risk; // Risiko

    /**
     * Konstruktor zur Initialisierung einer User Story mit allen Attributen.
     * Berechnet die Priorität basierend auf dem Mehrwert, der Strafe, dem Aufwand und dem Risiko.
     *
     * @param ID                die eindeutige ID der User Story
     * @param title             der Titel der User Story
     * @param acceptanceCriteria das Akzeptanzkriterium der User Story
     * @param project           das zugehörige Projekt der User Story
     * @param value             der Mehrwert der User Story (1=niedrig - 5=hoch)
     * @param penalty           die Strafe der User Story (1=niedrig - 5=hoch)
     * @param effort            der Aufwand der User Story (1=niedrig - 5=hoch)
     * @param risk              das Risiko der User Story (1=niedrig - 5=hoch)
     */
    public UserStory(Integer ID, String title, String acceptanceCriteria, String project, Integer value, Integer penalty, Integer effort, Integer risk) {
        this.ID = ID;
        this.title = title;
        this.acceptanceCriteria = acceptanceCriteria;
        this.project = project;
        this.value = value;
        this.penalty = penalty;
        this.effort = effort;
        this.risk = risk;
    }

    public String analyze(){
        return "";
    }

    /**
     * Gibt die ID der User Story zurück.
     *
     * @return die ID der User Story
     */
    public Integer getID() {
        return this.ID;
    }

    /**
     * Gibt den Titel der User Story zurück.
     *
     * @return der Titel der User Story
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gibt das Akzeptanzkriterium der User Story zurück.
     *
     * @return das Akzeptanzkriterium der User Story
     */
    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    /**
     * Gibt das Projekt der User Story zurück.
     *
     * @return das Projekt der User Story
     */
    public String getProject() {
        return project;
    }


    /**
     * Gibt die berechnete Priorität nach Gloger der User Story zurück.
     *
     * @return die Priorität der User Story
     */
    public double getPriority() {
        return (double) (this.value + this.penalty) / (this.effort + this.risk);
    }
}
