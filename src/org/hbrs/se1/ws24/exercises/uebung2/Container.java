package org.hbrs.se1.ws24.exercises.uebung2;

import java.util.ArrayList;
import java.util.List;

public class Container {
    List<Member> memberList = new ArrayList<>();

    public void addMember(Member member) throws ContainerException {
        if(memberList.contains(member)){
            throw new ContainerException();
        }else {
            memberList.add(member);
        }
    }

    public String deleteMember(Integer id) {
        for (Member member : memberList) {
            if (member.getID().equals(id)) {
                memberList.remove(member); // Member entfernen
                return "Member with ID " + id + " has been removed: " + member.toString();
            }
        }
        if (memberList.isEmpty()) {
            return "Error! There are no Members";
        } else {
            return "Error! No Member has this ID";
        }
    }

    public int size() {
        return memberList.size();
    }

    public void dump(){
        System.out.println(memberList.toString());
    }
}
