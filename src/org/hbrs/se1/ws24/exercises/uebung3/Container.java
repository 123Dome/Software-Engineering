package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> memberList = new ArrayList<>();

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

    public void dump(){
        memberList.forEach(System.out::println);
    }
}
