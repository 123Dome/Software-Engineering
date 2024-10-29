package org.hbrs.se1.ws24.exercises.uebung4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserStoryView {

    public void dump(List<UserStory> userStories) {
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