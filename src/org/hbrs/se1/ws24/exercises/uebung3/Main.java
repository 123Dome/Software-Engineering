package org.hbrs.se1.ws24.exercises.uebung3;

public class Main {
    public static void main(String[] args) throws ContainerException {
        Container container = Container.getInstance();
        Client client = new Client(container);
    }
}
