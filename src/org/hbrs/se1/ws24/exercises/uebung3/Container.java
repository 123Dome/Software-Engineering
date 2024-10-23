package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Container repräsentiert einen Singleton-Container, der Member-Objekte speichert.
 * Diese Klasse bietet Methoden zum Hinzufügen, Entfernen, Speichern und Laden von Member-Objekten.
 * Sie unterstützt das Singleton-Pattern, um sicherzustellen, dass nur eine Instanz existiert.
 * Zusätzlich kann eine Persistenzstrategie definiert werden, um die Member-Objekte persistent zu speichern.
 */
public class Container implements Serializable {
    private List<Member> memberList = new ArrayList<>();
    private static Container instance;
    private PersistenceStrategy<Member> memberPersistenceStrategy;

    /**
     * Privater Konstruktor, um die direkte Instanziierung von außen zu verhindern.
     * Dies stellt sicher, dass die Klasse nur über die Methode getInstance() instanziiert werden kann.
     */
    private Container(){}

    /**
     * Gibt die einzige Instanz der Container-Klasse zurück.
     * Implementierung des Singleton-Patterns.
     *
     * @return die einzige Instanz der Container-Klasse.
     */
    public static synchronized Container getInstance(){
        if(instance == null) instance = new Container();
        return instance;
    }

    /**
     * Erstellt und gibt eine separate Instanz des Containers zurück, die für Testzwecke verwendet werden kann.
     * Diese Methode ignoriert das Singleton-Pattern.
     *
     * @return eine neue Instanz der Container-Klasse.
     */
    public static Container forTestPurposeOnly(){
        return new Container();
    }

    /**
     * Setzt die Persistenzstrategie, die zum Speichern und Laden von Member-Objekten verwendet wird.
     *
     * @param persistenceStrategy die zu verwendende Persistenzstrategie.
     */
    public void setMemberPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy){
        memberPersistenceStrategy = persistenceStrategy;
    }

    /**
     * Fügt ein Member-Objekt zur Liste hinzu.
     * Wenn das Member-Objekt bereits vorhanden ist, wird eine ContainerException geworfen.
     *
     * @param member das hinzuzufügende Member-Objekt.
     * @throws ContainerException wenn das Member-Objekt bereits in der Liste vorhanden ist.
     */
    public void addMember(Member member) throws ContainerException {
        if(memberList.contains(member)){
            throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }else {
            memberList.add(member);
        }
    }

    /**
     * Entfernt ein Member-Objekt aus der Liste basierend auf der angegebenen ID.
     * Wenn kein Member mit der angegebenen ID existiert, wird eine entsprechende Fehlermeldung zurückgegeben.
     *
     * @param id die ID des zu entfernenden Member-Objekts.
     * @return eine Nachricht, ob das Entfernen erfolgreich war oder nicht.
     */
    public String deleteMember(Integer id) {
        if (memberList.isEmpty()) {
            return "Fehler! Es existieren noch keine Member!";
        }
        for (Member member : memberList) {
            if (member.getID().equals(id)) {
                memberList.remove(member);
                return "Member mit der ID " + id + " wurde entfernt!";
            }
        }
        return "Fehler! Kein Member hat diese ID!";
    }

    /**
     * Gibt die Anzahl der Member-Objekte in der Liste zurück.
     *
     * @return die Anzahl der Member-Objekte.
     */
    public int size() {
        return memberList.size();
    }

    /**
     * Gibt die aktuelle Liste der Member-Objekte zurück.
     *
     * @return die Liste der Member-Objekte.
     */
    public List<Member> getCurrentList() {
        return memberList;
    }

    /**
     * Speichert die Liste der Member-Objekte unter Verwendung der eingestellten Persistenzstrategie.
     * Wenn keine Strategie gesetzt wurde, wird eine PersistenceException geworfen.
     *
     * @throws PersistenceException wenn keine Strategie gesetzt ist oder die Implementierung der Strategie fehlschlägt.
     */
    public void store() throws PersistenceException {
        if(memberPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            memberPersistenceStrategy.save(memberList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    /**
     * Lädt die Liste der Member-Objekte unter Verwendung der eingestellten Persistenzstrategie.
     * Wenn keine Strategie gesetzt wurde, wird eine PersistenceException geworfen.
     *
     * @throws PersistenceException wenn keine Strategie gesetzt ist oder die Implementierung der Strategie fehlschlägt.
     */
    public void load() throws PersistenceException {
        if(memberPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            memberList = memberPersistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }
}
