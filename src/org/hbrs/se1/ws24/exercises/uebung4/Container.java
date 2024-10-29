package org.hbrs.se1.ws24.exercises.uebung4;


import org.hbrs.se1.ws24.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container <E extends UserStory>{
    private static Container<UserStory> instance;
    private List<E> itemList = new ArrayList<E>();
    private PersistenceStrategy<E> itemPersistenceStrategy;

    private Container(){};

    public static Container<UserStory> getInstance(){
        if(instance == null){
            synchronized (Container.class){
                if(instance == null){
                    instance = new Container();
                }
            }
        }
        return instance;
    }

    public void setUserStoriesPersistenceStrategy(PersistenceStrategy<E> persistenceStrategy){
        this.itemPersistenceStrategy = persistenceStrategy;
    }

    public void addItem(E item) throws ContainerException {
        if(itemList.contains(item)){
            throw new ContainerException("Item existiert bereits!");
        } else {
            itemList.add(item);
        }
    }

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

    public void store() throws PersistenceException {
        if(itemPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            itemPersistenceStrategy.save(itemList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    public void load() throws PersistenceException {
        if(itemPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            itemList = itemPersistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    public Integer size(){
        return itemList.size();
    }

    public List<E> getItemList() {
        return this.itemList;
    }
}
