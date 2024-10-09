package org.hbrs.se1.ws24.exercises.uebung2;

public class ConcreteMember implements Member{
    Integer id;

    public ConcreteMember(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return id;
    }
    @Override
    public String toString() {
        return "Member (ID = " + id + ")";
    }
}
