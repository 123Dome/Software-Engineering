package org.hbrs.se1.ws24.exercises.uebung9;

import org.hbrs.se1.ws24.demo.kapitel6.Buchung;

import java.util.ArrayList;
import java.util.List;

public class BuchungDAO {

    private List<Buchung> buchungen;

    public BuchungDAO() {
        buchungen = new ArrayList<>();
    }

    public void createBuchung(Buchung b) {
        buchungen.add(b);
        System.out.println("Buchung erstellt: " + b.getID());
    }

    public Buchung readBuchung(String id) {
        for (Buchung b : buchungen) {
            if (b.getID().equals(id)) {
                return b;
            }
        }
        System.out.println("Buchung nicht gefunden: " + id);
        return null;
    }

    public void updateBuchung(Buchung b) {
        for (int i = 0; i < buchungen.size(); i++) {
            if (buchungen.get(i).getID().equals(b.getID())) {
                buchungen.set(i, b);
                System.out.println("Buchung aktualisiert: " + b.getID());
                return;
            }
        }
        System.out.println("Buchung nicht gefunden: " + b.getID());
    }

    public void deleteBuchung(String id) {
        for (int i = 0; i < buchungen.size(); i++) {
            if (buchungen.get(i).getID().equals(id)) {
                buchungen.remove(i);
                System.out.println("Buchung gelöscht: " + id);
                return;
            }
        }
        System.out.println("Buchung nicht gefunden: " + id);
    }
}
