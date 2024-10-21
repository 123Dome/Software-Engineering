package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.List;

/**
 * Die Klasse Client ist dafür verantwortlich, eine Liste von Member-Objekten in einen Container einzufügen
 * und diese anschließend mithilfe der MemberView anzuzeigen.
 */
public class Client {

    /**
     * Konstruktor der Klasse Client. Fügt drei Member-Objekte in den übergebenen Container ein.
     * Die Member-Objekte werden der Liste entnommen und anschließend angezeigt.
     *
     * @param container Der Container, in den die Member-Objekte eingefügt werden.
     * @throws ContainerException wenn beim Hinzufügen eines Member-Objekts ein Fehler auftritt, z.B. wenn ein Member bereits vorhanden ist.
     */
    public Client(Container container) throws ContainerException {
        for(int i = 1; i <= 3; i++) container.addMember(new ConcreteMember(i));
        List<Member> memberList = container.getCurrentList();
        MemberView.dumb(memberList);
    }
}
