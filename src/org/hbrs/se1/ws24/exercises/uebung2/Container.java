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

    //TODO Checken ob ein Member mit dieser ID in der Liste existiert
    public String deleteMember(Integer id) {
        if(memberList.contains(member)) return memberList.remove(memberList.indexOf(id)).toString();
        else if(memberList.isEmpty()) return "Error! There are no Members";
        else return "Error! No Member has this ID";
    }

    public int size() {
        return memberList.size();
    }

    public void dump(){
        System.out.println(memberList.toString());
    }
}
