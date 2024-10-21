package org.hbrs.se1.ws24.exercises.uebung3;

import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable {
    private List<Member> memberList = new ArrayList<>();
    private static Container instance;
    private PersistenceStrategy<Member> memberPersistenceStrategy;

    // Privater Konstruktor, um die direkte Instanziierung von außen zu verhindern
    private Container(){}

    // Statische Methode zur Erzeugung oder Rückgabe der einzigen Instanz
    public static Container getInstance(){
        if(instance == null) instance = new Container();
        return instance;
    }

    public static Container forTestPurposeOnly(){
        return new Container();
    }

    public void setMemberPersistenceStrategy(PersistenceStrategy<Member> persistenceStrategy){
        memberPersistenceStrategy = persistenceStrategy;
    }

    public void addMember(Member member) throws ContainerException {
        if(memberList.contains(member)){
            throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }else {
            memberList.add(member);
        }
    }

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

    public int size() {
        return memberList.size();
    }

    public List<Member> getCurrentList() {
        return memberList;
    }

    public void store() throws PersistenceException {
        if(memberPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            memberPersistenceStrategy.save(memberList);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }

    public void load() throws PersistenceException {
        if(memberPersistenceStrategy == null) throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie wurde festgelegt!");
        try{
            memberList = memberPersistenceStrategy.load();
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Strategie wurde noch nicht (richtig) implementiert!");
        }
    }
}
