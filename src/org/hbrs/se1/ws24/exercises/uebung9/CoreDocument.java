package org.hbrs.se1.ws24.exercises.uebung9;

public abstract class CoreDocument {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract int getSizeInBytes();
}