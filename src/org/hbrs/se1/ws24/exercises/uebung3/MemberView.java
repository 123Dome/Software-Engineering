package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.List;

/**
 * Die Klasse MemberView bietet eine statische Methode zur Ausgabe einer Liste von Member-Objekten.
 * Sie dient zur einfachen Darstellung der Inhalte einer Member-Liste auf der Konsole.
 */
public class MemberView {
    /**
     * Gibt die übergebene Liste von Member-Objekten auf der Konsole aus.
     * Jedes Member-Objekt wird in einer neuen Zeile ausgegeben.
     *
     * @param memberList die Liste der Member-Objekte, die ausgegeben werden sollen.
     */
    public static void dumb(List<Member> memberList){
        memberList.forEach(System.out::println);
    }
}
