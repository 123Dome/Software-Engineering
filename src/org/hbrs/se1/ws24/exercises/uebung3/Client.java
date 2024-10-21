package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.List;

public class Client {
    private List<Member> memberList;

    public Client(Container container) throws ContainerException {
        for(int i = 1; i <= 3; i++) container.addMember(new ConcreteMember(i));
        this.memberList = container.getCurrentList();
        MemberView.dumb(this.memberList);
    }
}
