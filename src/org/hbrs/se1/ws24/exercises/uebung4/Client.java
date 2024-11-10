package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;
import org.hbrs.se1.ws24.exercises.uebung4.userStory.UserStory;
import org.hbrs.se1.ws24.exercises.uebung4.userStory.UserStoryView;

import java.util.Scanner;

/**
 * Die Klasse Client ist die Hauptklasse des User Story Management Systems.
 * Sie bietet eine textbasierte Benutzerschnittstelle zur Verwaltung von User Stories,
 * einschließlich Hinzufügen, Speichern, Laden, und Auflisten.
 */
public class Client {
    private static final Container<UserStory> container = Container.getInstance();
    private static final PersistenceStrategyStream<UserStory> persistenceStrategy = new PersistenceStrategyStream<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "src/org/hbrs/se1/ws24/exercises/uebung4/UserStories.ser";

    public static void main(String[] args) {
        initializePersistence();
        processUserCommands();
    }

    /**
     * Initialisiert die Persistenzstrategie, indem der Speicherort gesetzt und
     * dem Container übergeben wird.
     */
    private static void initializePersistence() {
        persistenceStrategy.setLocation(FILE_PATH);
        container.setUserStoriesPersistenceStrategy(persistenceStrategy);
        System.out.println("Willkommen zum User Story Management System. Geben Sie 'help' für eine Liste der Befehle ein.");
    }

    /**
     * Verarbeitet die Benutzereingaben und führt entsprechende Aktionen
     * wie Hinzufügen, Speichern und Laden der User Stories aus.
     */
    private static void processUserCommands() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 3);
            String command = parts[0];
            try {
                switch (command) {
                    case "enter" -> enterUserStory();
                    case "store" -> container.store();
                    case "load" -> container.load();
                    case "dump" -> {
                        if (parts.length > 2) UserStoryView.dump(container.getItemList(), parts[1], parts[2]);
                        else UserStoryView.dump(container.getItemList());
                    }
                    case "exit" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    case "help" -> printHelp();
                    default -> System.out.println("Unbekannter Befehl. Geben Sie 'help' für eine Liste der Befehle ein.");
                }
            } catch (Exception e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    /**
     * Fordert den Benutzer zur Eingabe einer neuen User Story auf und fügt diese zum Container hinzu.
     *
     * @throws ContainerException wenn die User Story bereits existiert
     */
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

    /**
     * Liest eine Zeichenkette von der Konsole ein.
     *
     * @param prompt Der Anzeigetext für die Eingabeaufforderung
     * @return die vom Benutzer eingegebene Zeichenkette
     */
    private static String readString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    /**
     * Liest eine Ganzzahl von der Konsole ein und behandelt fehlerhafte Eingaben.
     *
     * @param prompt Der Anzeigetext für die Eingabeaufforderung
     * @return die vom Benutzer eingegebene Ganzzahl
     */
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

    /**
     * Liest eine Ganzzahl aus einem bestimmten Wertebereich von der Konsole ein.
     *
     * @param prompt Der Anzeigetext für die Eingabeaufforderung
     * @param min    Der minimale erlaubte Wert
     * @param max    Der maximale erlaubte Wert
     * @return die vom Benutzer eingegebene Ganzzahl im definierten Bereich
     */
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

    /**
     * Zeigt eine Liste der verfügbaren Befehle und deren Beschreibung an.
     */
    private static void printHelp() {
        System.out.println("Verfügbare Befehle:");
        System.out.println("enter - Eingabe einer neuen User Story");
        System.out.println("store - Speichert die User Stories in einer Datei");
        System.out.println("load - Lädt User Stories aus einer Datei");
        System.out.println("dump - Ausgabe aller User Stories, sortiert nach Priorität");
        System.out.println("exit - Beendet das Programm");
        System.out.println("help - Zeigt diese Hilfe an");
    }
}
