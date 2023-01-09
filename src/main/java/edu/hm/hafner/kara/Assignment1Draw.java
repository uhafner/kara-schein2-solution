package edu.hm.hafner.kara;

import static de.i8k.karalight.Kara.*;

/**
 * KaraLight: Leere Vorlage für die elektronische Prüfung zum Scheinerwerb.
 */
public class Assignment1Draw {
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
     *         Dieser Parameter wird von Kara nicht benutzt, muss aber bestehen bleiben, damit die KaraLight
     *         Oberfläche in der Entwicklungsumgebung gestartet werden kann. Dieser Parameter ist auch erforderlich,
     *         damit die automatisierte Auswertung der Ergebnisse funktioniert.
     */
    public static void main(final String... unused) {
        var width = computeLength();
        turnRight();
        var height = computeLength();
        turnLeft();

        var x0 = askNumber("Startpunkt x Koordinate, d.h. Spalte 0 bis (breite-1): ");
        var y0 = askNumber("Startpunkt y Koordinate, d.h. Zeile 0 bis (höhe-1): ");
        var recWidth = askNumber("Breite Rechteck: ");
        var recHeight = askNumber("Höhe Rechteck: ");

        if (x0 >= 0 && x0 < width && y0 >= 0 && y0 < height) {
            walk(x0);
            turnRight();
            walk(y0);
            turnLeft();

            var distanceX = Math.min(recWidth, width - x0);
            var distanceY = Math.min(recHeight, height - y0);
            draw(distanceX);
            if (x0 + recWidth <= width) {
                draw(distanceY);
            }
            else {
                walk(distanceY - 1);
                turnRight();
            }
            if (y0 + recHeight <= height) {
                draw(distanceX);
            }
            else {
                walk(distanceX - 1);
                turnRight();
            }
            draw(distanceY);
        }
    }

    private static void draw(final int steps) {
        for (int i = 0; i < steps; i++) {
            if (!isOnLeaf()) {
                putLeaf();
            }
            move();
        }
        turnLeft();
        turnLeft();
        move();
        turnLeft();
    }

    private static void walk(final int steps) {
        for (int i = 0; i < steps; i++) {
            move();
        }
    }

    private static int computeLength() {
        int width = 0;

        putLeaf();
        do {
            move();
            width++;
        } while (!isOnLeaf());
        pickLeaf();

        return width;
    }
}
