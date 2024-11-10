package org.hbrs.se1.ws24.exercises.uebung4;


import org.hbrs.se1.ws24.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws24.exercises.uebung4.userStory.UserStory;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Container ist eine generische Sammlung zur Verwaltung von User Story-Objekten.
 * Sie implementiert ein Singleton-Pattern, um sicherzustellen, dass nur eine Instanz der Klasse existiert.
 * Die Klasse unterstützt das Hinzufügen und Entfernen von User Stories, sowie das Laden und Speichern
 * durch eine wählbare Persistenzstrategie.
 *
 * @param <E> Der Typ der Elemente, die im Container gespeichert werden (hier User Stories).
 */
public class Container <E extends UserStory>{
    private static Container<UserStory> instance;
    private List<E> itemList = new ArrayList<>();
    private PersistenceStrategy<E> persistenceStrategy;

    private Container(){};

    /**
     * Liefert die Singleton-Instanz des Containers.
     *
     * @return die einzige Instanz des Containers
     */
    public static Container<UserStory> getInstance() {
        if (instance == null) {
            synchronized (Container.class) {
                if (instance == null) {
                    instance = new Container<>();
                }
            }
        }
        return instance;
    }

    /**
     * Setzt die Persistenzstrategie des Containers.
     *
     * @param strategy die zu verwendende Persistenzstrategie
     */
    public void setUserStoriesPersistenceStrategy(PersistenceStrategy<E> strategy){
        this.persistenceStrategy = strategy;
    }

    /**
     * Fügt ein neues Element zum Container hinzu.
     *
     * @param item das hinzuzufügende Element
     * @throws ContainerException wenn das Element bereits im Container existiert
     */
    public void addItem(E item) throws ContainerException {
        if (contains(item)) {
            throw new ContainerException("Item existiert bereits!");
        }
        itemList.add(item);
    }

    /**
     * Entfernt ein Element aus dem Container basierend auf der ID.
     *
     * @param id die ID des zu entfernenden Elements
     * @return eine Bestätigungsmeldung oder eine Fehlermeldung, wenn das Element nicht gefunden wurde
     */
    public String deleteItem(Integer id) {
        if(itemList.isEmpty()){
            return "Fehler! Es existieren keine Items!";
        }
        for(E item : itemList){
            if(item.getID().equals(id)){
                itemList.remove(item);
                return "Item wurde entfernt!";
            }
        }
        return "Fehler! Item wurde nicht gefunden!";
    }

    /**
     * Speichert alle Elemente des Containers persistent.
     *
     * @throws PersistenceException wenn keine Strategie festgelegt ist oder ein Fehler beim Speichern auftritt
     */
    public void store() throws PersistenceException {
        if(persistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            persistenceStrategy.save(itemList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    /**
     * Lädt alle Elemente aus dem persistenten Speicher in den Container.
     *
     * @throws PersistenceException wenn keine Strategie festgelegt ist oder ein Fehler beim Laden auftritt
     */
    public void load() throws PersistenceException {
        if(persistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            itemList = persistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    /**
     * Gibt die Anzahl der Elemente im Container zurück.
     *
     * @return die Anzahl der gespeicherten Elemente
     */
    public Integer size(){
        return itemList.size();
    }

    /**
     * Gibt die Liste aller gespeicherten Elemente zurück.
     *
     * @return die Liste der Elemente im Container
     */
    public List<E> getItemList() {
        return this.itemList;
    }

    public boolean contains(E item) {
        Integer id = item.getID();
        for(E rec : itemList){
            if ( rec.getID() == id ) {
                return true;
            }
        }
        return false;
    }
}
