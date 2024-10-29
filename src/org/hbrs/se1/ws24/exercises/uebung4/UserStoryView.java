package org.hbrs.se1.ws24.exercises.uebung4;

import java.util.List;

public class UserStoryView {
    public void dump(List<UserStory> userStories){
        if(userStories.isEmpty()){
            System.out.println("Keine User Story gefunden!");
        } else {
            System.out.printf("%-10s %-20s %-15s %-15s%n", "ID", "Titel", "Projekt", "Priorität");
            for (UserStory story : userStories) {
                System.out.printf("%-10s %-20s %-15s %-15.2f%n",
                        story.getID(), story.getTitle(), story.getProject(), story.getPriority());
            }
        }
    }
}
