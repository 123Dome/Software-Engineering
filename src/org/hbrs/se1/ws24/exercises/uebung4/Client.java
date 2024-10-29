package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.Scanner;

public class Client {
    private static final Container<UserStory> container = Container.getInstance();
    private static final PersistenceStrategyStream<UserStory> persistenceStrategy = new PersistenceStrategyStream<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "src/org/hbrs/se1/ws24/exercises/uebung4/UserStory.ser";

    public static void main(String[] args) throws PersistenceException {
        persistenceStrategy.setLocation(FILE_PATH);
        container.setUserStoriesPersistenceStrategy(persistenceStrategy);
        System.out.println("Willkommen zum User Story Management System. Geben Sie 'help' für eine Liste der Befehle ein.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            try {
                switch (input) {
                    case "enter":
                        enterUserStory();
                        break;
                    case "store":
                        container.store();
                        break;
                    case "load":
                        container.load();
                        break;
                    case "dump":
                        UserStoryView.dump(container.getItemList());
                        break;
                    case "exit":
                        System.out.println("Goodbye!");
                        return;
                    case "help":
                        printHelp();
                        break;
                    default:
                        System.out.println("Unbekannter Befehl. Geben Sie 'help' für eine Liste der Befehle ein.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void enterUserStory() throws ContainerException {
        Integer id = readInteger("ID");
        String title = readString("Titel");
        String acceptanceCriteria = readString("Akzeptanzkriterium");
        String project = readString("Projekt");

        Integer value = readBoundedInteger("Mehrwert (1=niedrig - 5=hoch)", 1, 5);
        Integer penalty = readBoundedInteger("Strafe (1=niedrig - 5=hoch)", 1, 5);
        Integer effort = readBoundedInteger("Aufwand (1=niedrig - 5=hoch)", 1, 5);
        Integer risk = readBoundedInteger("Risiko (1=niedrig - 5=hoch)", 1, 5);

        UserStory story = new UserStory(id, title, acceptanceCriteria, project, value, penalty, effort, risk);
        container.addItem(story);
        System.out.println("User Story hinzugefügt.");
    }

    private static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    private static Integer readInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Ganzzahl eingeben.");
            }
        }
    }

    private static Integer readBoundedInteger(String prompt, int min, int max) {
        while (true) {
            int value = readInteger(prompt);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Bitte eine Zahl zwischen " + min + " und " + max + " eingeben.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Verfügbare Befehle:");
        System.out.println("enter - Eingabe einer neuen User Story");
        System.out.println("store [filename] - Speichert die User Stories in einer Datei");
        System.out.println("load [filename] - Lädt User Stories aus einer Datei");
        System.out.println("dump - Ausgabe aller User Stories, sortiert nach Priorität");
        System.out.println("exit - Beendet das Programm");
        System.out.println("help - Zeigt diese Hilfe an");
    }
}
