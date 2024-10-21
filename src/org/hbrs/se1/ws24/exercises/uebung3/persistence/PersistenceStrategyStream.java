package org.hbrs.se1.ws24.exercises.uebung3.persistence;
import org.hbrs.se1.ws24.exercises.uebung2.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse PersistenceStrategyStream implementiert die Persistenzstrategie, um Objekte vom Typ Member
 * auf eine Datei zu speichern und aus einer Datei zu laden.
 * Diese Klasse nutzt Java-Streams, um Objekte zu serialisieren und zu deserialisieren.
 *
 * @param <E> Der Typ der Objekte, die gespeichert und geladen werden.
 */
public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // Pfad zur Datei, in der die Objekte gespeichert werden
    private String location = "objects.ser";

    /**
     * Methode, um den Speicherort der Datei zu setzen. Diese Methode wird hauptsächlich für Unit-Tests verwendet.
     *
     * @param location der Pfad zur Datei oder zum Verzeichnis, in dem die Objekte gespeichert werden sollen.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Speichert eine Liste von Objekten auf die Festplatte.
     * Die Objekte werden in der Datei gespeichert, die durch den Speicherort (location) angegeben wird.
     * Es wird ein ObjectOutputStream verwendet, um die Liste der Objekte zu serialisieren.
     *
     * @param member die Liste der Objekte, die gespeichert werden sollen.
     * @throws PersistenceException wenn ein Fehler beim Speichern auftritt, z.B. wenn die Datei nicht verfügbar ist.
     */
    @Override
    public void save(List<E> member) throws PersistenceException  {
        try (
            FileOutputStream fos = new FileOutputStream(location);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
            )
        {
            oos.writeObject(member);
        }
         catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Objekt konnte nicht zu folgender Datei hinzugefügt werden: " + location);
        }

    }

    /**
     * Lädt eine Liste von Objekten von der Festplatte.
     * Es wird ein ObjectInputStream verwendet, um die Liste der Objekte aus der Datei zu deserialisieren.
     *
     * @return die Liste der geladenen Objekte.
     * @throws PersistenceException wenn ein Fehler beim Laden der Datei auftritt, z.B. wenn die Datei nicht verfügbar ist oder ein Leseproblem auftritt.
     */
    @Override
    public List<E> load() throws PersistenceException  {
        List<E> objectList = new ArrayList<>();
        try(
                FileInputStream fis = new FileInputStream(location);
                ObjectInputStream ois = new ObjectInputStream(fis)
            )
        {
            Object object = ois.readObject();
            if(object instanceof List<?>) objectList = (List<E>) object;
            else throw new PersistenceException(PersistenceException.ExceptionType.ReadError, "Objekt konnte nicht richtig ausgelesen werden aus folgender Datei: " + location + "!");
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Objekte konnten nicht aus folgender Datei ausgelesen werden: " + location + "!");
        }
        return objectList;
    }
}
