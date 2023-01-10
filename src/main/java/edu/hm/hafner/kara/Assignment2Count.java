package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: Leere Vorlage für die elektronische Prüfung zum Scheinerwerb.
 */
public class Assignment2Count {
    /**
     * Die {@code main} Methode ist der Ausgangspunkt für KaraLight. Hier wird direkt in Java programmiert, folgende
     * Kara-Befehle können verwendet werden, um Kara zu steuern:
     * <ul>
     *   <li>{@code move()} - Kara bewegt sich einen Schritt nach vorn. Das geht nur, wenn vor Kara kein Baum ist!
     *   Wenn vor Kara ein Pilz ist, schiebt Kara den Pilz eine Position weiter.
     *   (Das setzt wiederum voraus, dass der Platz vor dem Pilz frei ist). </li>
     *   <li>{@code turnRight()} bzw. {@code turnLeft()} - Kara dreht sich nach rechts bzw. links</li>
     *   <li>{@code pickLeaf()} - Kara nimmt ein Blatt auf (geht nur, wenn eins da ist!)</li>
     *   <li>{@code putLeaf()} - Kara legt ein Blatt ab (geht nur, wenn keins da ist!)</li>
     *   <li>{@code say(...)} - Kara gibt einen Text in einem Fenster aus.</li>
     *   <li>{@code askNumber(...)} - Kara fragt nach einer Zahl, die den Ablauf des Programms variabel gestaltet.</li>
     * </ul>
     * Zusätzlich stehen Ihnen die folgenden Abfragen zur Verfügung:
     * <ul>
     *   <li>{@code isMushroomInFront()} - liefert {@code true}, wenn vor Kara ein Pilz steht</li>
     *   <li>{@code isTreeInFront()} - liefert {@code true}, wenn vor Kara ein Baum steht</li>
     *   <li>{@code isTreeLeft()} - liefert {@code true}, wenn links von Kara ein Baum steht</li>
     *   <li>{@code isTreeRight()} - liefert {@code true}, wenn rechts von Kara ein Baum steht</li>
     *   <li>{@code isOnLeaf()} - liefert {@code true}, wenn Kara auf einem Blatt steht</li>
     * </ul>
     *
     * @param unused
     *         Dieser Parameter wird von Kara nicht benutzt, muss aber bestehen bleiben, damit die KaraLight Oberfläche
     *         in der Entwicklungsumgebung gestartet werden kann. Dieser Parameter ist auch erforderlich, damit die
     *         automatisierte Auswertung der Ergebnisse funktioniert.
     */
    public static void main(final String... unused) {
        int leaves = count();

        say("Leaves: " + leaves);
    }

    private static int count() {
        int leaves = 0;

        for (;;) {
            turnRight();
            leaves += countLeavesInLine();

            turnAround();
            leaves += moveToTree();
            turnRight();
            if (isTreeInFront()) {
                return leaves;
            }
            move();
        }
    }

    private static void turnAround() {
        turnLeft();
        turnLeft();
    }

    private static int countLeavesInLine() {
        int leaves = isOnLeaf() ? 1 : 0;
        while (!isTreeInFront()) {
            if (isMushroomInFront()) {
                walkAroundMushroom();
            }
            else {
                move();
            }
            if (isOnLeaf()) {
                leaves++;
            }
        }
        return leaves;
    }

    private static void walkAroundMushroom() {
        turnLeft();
        move();
        turnRight();
        move();
        move();
        turnRight();
        move();
        turnLeft();
    }

    private static int moveToTree() {
        int leaves = 0;
        while (!isTreeInFront()) {
            if (isMushroomInFront()) {
                walkAroundMushroom();
                turnAround();
                move();
                leaves = isOnLeaf() ? 1 : 0;
                turnAround();
                move();
            }
            else {
                move();
            }
        }
        return leaves;
    }
}
