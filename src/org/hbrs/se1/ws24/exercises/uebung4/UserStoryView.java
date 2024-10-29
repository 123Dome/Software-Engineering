package org.hbrs.se1.ws24.exercises.uebung4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse UserStoryView bietet eine Methode zur konsolenbasierten
 * Ausgabe einer Liste von User Stories. Die Ausgabe ist nach Priorität
 * absteigend sortiert und enthält die wichtigsten Eigenschaften jeder
 * User Story, wie ID, Titel, Akzeptanzkriterium, Projekt und Priorität.
 */
public class UserStoryView {

    /**
     * Gibt eine Liste von User Stories formatiert in der Konsole aus.
     * Die User Stories werden dabei absteigend nach ihrer Priorität sortiert.
     * Die Ausgabe enthält die ID, den Titel, das Akzeptanzkriterium, das Projekt und die berechnete Priorität jeder User Story.
     *
     * @param userStories die Liste der User Stories, die ausgegeben werden sollen.
     */
    public static void dump(List<UserStory> userStories) {
        if (userStories.isEmpty()) {
            System.out.println("Keine User Story gefunden!");
        } else {
            System.out.printf("%-10s %-20s %-20s %-15s %-15s%n",
                    "ID", "Titel", "Akzeptanzkriterium", "Projekt", "Priorität");

            // Stream-basierte Ausgabe mit Filter-Map-Reduce-Pattern
            String result = userStories.stream()
                    .sorted(Comparator.comparingDouble(UserStory::getPriority).reversed()) // Sortiert nach Priorität (absteigend)
                    .map(story -> String.format("%-10s %-20s %-20s %-15s %-15.2f",
                            story.getID(), story.getTitle(), story.getAcceptanceCriteria(), story.getProject(), story.getPriority()))
                    .collect(Collectors.joining("\n"));
            System.out.println(result);
        }
    }
}