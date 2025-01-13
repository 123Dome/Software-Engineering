package org.hbrs.se1.ws24.exercises.uebung9;

public class TestClient {
    public static void main(String[] args) {
        // Dokumente erstellen
        ComplexDocument doc0 = new ComplexDocument();
        doc0.setId(0);

        TextDocument doc2 = new TextDocument("Die Klausur im Fach SE findet bald statt!", TextDocument.Encoding.UTF8);
        doc2.setId(2);

        ComplexDocument doc3 = new ComplexDocument();
        doc3.setId(3);

        GraficDocument doc4 = new GraficDocument("localhost:8080");
        doc4.setId(4);

        TextDocument doc5 = new TextDocument("Software Engineering I ist eine Vorlesung in den Studiengaengen BIS und BCS", TextDocument.Encoding.UTF32);
        doc5.setId(5);

        // Hierarchie aufbauen
        doc0.addDocument(doc2);
        doc0.addDocument(doc3);
        doc3.addDocument(doc4);
        doc3.addDocument(doc5);

        // Gesamtgröße berechnen
        int totalSize = doc0.getSizeInBytes();
        System.out.println("Gesamtgröße der Dokumentstruktur: " + totalSize + " Bytes");
    }
}
