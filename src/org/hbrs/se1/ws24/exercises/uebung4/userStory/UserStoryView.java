package org.hbrs.se1.ws24.exercises.uebung4.userStory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse UserStoryView bietet Methoden zur Ausgabe von User Stories.
 * Sie kann alle User Stories ausgeben oder nur diejenigen, die einem bestimmten Kriterium entsprechen.
 */
public class UserStoryView {

    /**
     * Gibt alle User Stories aus der Liste aus, sortiert nach Priorität in absteigender Reihenfolge.
     *
     * @param userStories Die Liste der User Stories, die ausgegeben werden sollen.
     */
    public static void dump(List<UserStory> userStories) {
        dump(userStories, null, null); // Aufruf der überladenen Methode ohne Filter
    }

    /**
     * Gibt User Stories aus, die einem bestimmten Filterkriterium entsprechen,
     * sortiert nach Priorität in absteigender Reihenfolge.
     *
     * @param userStories    Die Liste der User Stories, die ausgegeben werden sollen.
     * @param filterCriteria Das Kriterium, nach dem gefiltert werden soll (z. B. "projekt", "id", "titel", etc.).
     * @param filterValue    Der Wert, nach dem gefiltert werden soll. Wenn `null`, wird kein Filter angewendet.
     */
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