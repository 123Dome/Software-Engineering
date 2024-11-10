package org.hbrs.se1.ws24.exercises.uebung4.userStory;

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
        dump(userStories, null, null); // Aufruf der überladenen Methode ohne Filter
    }

    public static void dump(List<UserStory> userStories, String filterCriteria, String filterValue) {
        if (userStories.isEmpty()) {
            System.out.println("Keine User Story gefunden!");
        } else {
            System.out.printf("%-10s %-20s %-20s %-15s %-15s%n",
                    "ID", "Titel", "Akzeptanzkriterium", "Projekt", "Priorität");

            String result = userStories.stream()
                    .filter(story -> {
                        if (filterCriteria != null && filterValue != null) {
                            String value = filterValue.toLowerCase();
                            return switch (filterCriteria.toLowerCase()) {
                                case "projekt" -> story.getProject().equalsIgnoreCase(value);
                                case "id" -> story.getID().toString().equals(value);
                                case "titel" -> story.getTitle().toLowerCase().contains(value);
                                case "akzeptanzkriterium" -> story.getAcceptanceCriteria().toLowerCase().contains(value);
                                case "priorität" -> story.getPriority() == Double.parseDouble(value);
                                default -> true;
                            };
                        }
                        return true;
                    })
                    .sorted(Comparator.comparingDouble(UserStory::getPriority).reversed())
                    .map(story -> String.format("%-10s %-20s %-20s %-15s %-15.2f",
                            story.getID(), story.getTitle(), story.getAcceptanceCriteria(), story.getProject(), story.getPriority()))
                    .collect(Collectors.joining("\n"));
            System.out.println(result);
        }
    }
}