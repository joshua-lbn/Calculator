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
    private JButton[] jButtonMode;
    private String[] textsMode;

    /**
     * Konstruktor, der die übergebene View-Instanz speichert und die lokalen Attribute aus der View ueber Getter erhaelt.
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
        jButtonMode = view.GetJButtonMode();
        textsMode = view.GetTextsMode();
    }

    /**
     * Methode, welche bei Knopfdrucken aufgerufen wird.
     *
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        /*
         * Alle JNumberButtons und im weiteren Verlauf die anderen Arrays werden geprueft.
         * Bei Uebereinstimmung des Eventursprungs mit einem Knopf wird:
         * der View der Befehl gegeben, die hinzugefuegten Zeichen an den Controller zu geben, sodass sie hinzugefuegt werden koennen.
         * der View der Befehl gegeben, nach Änderung der Darstellung (durch den Controller) diese zu aktualisieren.
         * die weitere Ausfuehrung der Methode aus Effizienzgruenden abgebrochen.
         */
        for (int i = 0; i < jButtonsNumber.length; i++) {
            if (e.getSource() == jButtonsNumber[i]) {
                view.Update("" + (i) + "");
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            if (e.getSource() == jButtonsLeft[i]) {
                view.Update(textsLeft[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            if (e.getSource() == jButtonsMiddle[i]) {
                view.Update(textsMiddle[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            if (e.getSource() == jButtonsRight[i]) {
                view.Update(textsRight[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            if (e.getSource() == jButtonsCursor[i]) {
                view.Update(textsCursor[i]);
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonMode.length; i++) {
            if (e.getSource() == jButtonMode[i]) {
                view.SwitchMode();
                return;
            }
        }

        if (e.getSource() == view.GetJMenuItemCone()) {
            System.out.println("Kegel");
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

        if (e.getSource() == view.GetJMenuItemSquare()) {
            Frame frame = new squareVolume();
            frame.setVisible(true);
            return;
        }
    }
}


