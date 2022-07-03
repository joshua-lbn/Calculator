package Calculator.view;

import javax.swing.*;
import java.awt.*;


/*
 * ProcessButtonInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessButtonInput implements java.awt.event.ActionListener {
    private View view;
    // Relevante Attribute: Arrays mit Knopfobjekten und Beschriftungen
    private JButton[] jButtonsNumber;
    private JButton[] jButtonsLeft;
    private String[] textsLeft;
    private JButton[] jButtonsMiddle;
    private String[] textsMiddle;
    private JButton[] jButtonsRight;
    private String[] textsRight;
    private JButton[] jButtonsCursor;
    private String[] textsCursor;
    private JButton[] jButtonsMode;

    /**
     * Konstruktor: übergebene View-Instanz speichern und lokale Attribute aus der View ueber Getter erhalten.
     *
     * @param v View-Instanz
     */
    public ProcessButtonInput(View v) {
        // View speichern
        view = v;
        // Getter aufrufen
        jButtonsNumber = view.GetJButtonsNumber();
        jButtonsLeft = view.GetJButtonsLeft();
        textsLeft = view.GetTextsLeft();
        jButtonsMiddle = view.GetJButtonsMiddle();
        textsMiddle = view.GetTextsMiddle();
        jButtonsRight = view.GetJButtonsRight();
        textsRight = view.GetTextsRight();
        jButtonsCursor = view.GetJButtonsCursor();
        textsCursor = view.GetTextsCursor();
        jButtonsMode = view.GetJButtonMode();
    }

    /**
     * Methode, welche bei Knopfdrucken aufgerufen wird.
     *
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        /** Alle JNumberButtons und im weiteren Verlauf die anderen Arrays bzw. Knoepfe werden geprueft.
         * Bei Uebereinstimmung des Eventursprungs mit einem Knopf wird:
         * der View der Befehl gegeben, die hinzugefuegten Zeichen an den Controller zu geben, sodass sie hinzugefuegt
         * werden koennen.
         * der View der Befehl gegeben, nach Änderung der Darstellung (durch den Controller) diese zu aktualisieren.
         * die weitere Ausfuehrung der Methode aus Effizienzgruenden abgebrochen.
         */

        if (e.getSource() == view.GetJMenuItemCone()) {
            System.out.println("Kegel");
            return;
        }
        if (e.getSource() == view.GetJMenuItemSquare()) {
            Frame frame = new ViewVolumeSquare();
            frame.setVisible(true);
            return;
        }
        if (e.getSource() == view.GetJMenuItemCylinder()) {
            System.out.println("Zylinder");
            return;
        }
        if (e.getSource() == view.GetJMenuItemSphere()) {
            System.out.println("sphere");
            return;
        }


        /**Bitte nicht löschen @Joshua
         *
         */


        if (e.getSource() == view.GetJMenuItemDecimal()) {
            //System.out.println("decimal");
            ViewNumeralSystem NumeralView = new ViewNumeralSystem();
            return;
        }
        if (e.getSource() == view.GetJMenuItemHexa()) {
            //System.out.println("hexa");
            ViewNumeralSystem NumeralView = new ViewNumeralSystem();
            return;
        }
        if (e.getSource() == view.GetJMenuItemBinary()) {
            //System.out.println("binary");
            ViewNumeralSystem NumeralView = new ViewNumeralSystem();
            return;
        }
    }
}
