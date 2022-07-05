package Calculator.view.calculator;

import Calculator.view.main.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/*
 * ProcessButtonInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche des Taschenrechners.
 * Programmtechnisch an die ViewCalculator-Klasse gegliedert.
 */
public class ProcessButtonInput implements java.awt.event.ActionListener {
    // Referenz auf andere Klasse
    private ViewCalculator viewCalculator;
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
     * Konstruktor: übergebene ViewCalculator-Instanz speichern und lokale Attribute aus derselben erhalten.
     * @param vC ViewCalculator-Instanz
     */
    public ProcessButtonInput(ViewCalculator vC) {
        // View speichern
        viewCalculator = vC;
        // Getter aufrufen
        jButtonsNumber = viewCalculator.GetJButtonsNumber();
        jButtonsLeft = viewCalculator.GetJButtonsLeft();
        textsLeft = viewCalculator.GetTextsLeft();
        jButtonsMiddle = viewCalculator.GetJButtonsMiddle();
        textsMiddle = viewCalculator.GetTextsMiddle();
        jButtonsRight = viewCalculator.GetJButtonsRight();
        textsRight = viewCalculator.GetTextsRight();
        jButtonsCursor = viewCalculator.GetJButtonsCursor();
        textsCursor = viewCalculator.GetTextsCursor();
        jButtonsMode = viewCalculator.GetJButtonMode();
    }

    /**
     * Methode, welche bei Knopfdrucken aufgerufen wird.
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(ActionEvent e) {
        /** Alle JNumberButtons und im weiteren Verlauf die anderen Arrays bzw. Knoepfe werden geprueft.
         * Bei Uebereinstimmung des Eventursprungs mit einem Knopf und im aktivierten Zustand wird:
         * der viewCalculator der Befehl gegeben, die hinzugefuegten Zeichen an den Controller zu geben, sodass sie
         * hinzugefuegt werden koennen.
         * der viewCalculator der Befehl gegeben, nach Änderung der Interna (durch den Controller) die Darstellung zu
         * aktualisieren.
         * die weitere Ausfuehrung der Methode aus Effizienzgruenden abzubrechen.
         */

        for (int i = 0; i < jButtonsNumber.length; i++) {
            if (e.getSource() == jButtonsNumber[i]) {
                viewCalculator.Update("" + (i) + "");
                viewCalculator.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsLeft.length; i++) {
            if (e.getSource() == jButtonsLeft[i]) {
                viewCalculator.Update(textsLeft[i]);
                viewCalculator.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            if (e.getSource() == jButtonsMiddle[i]) {
                viewCalculator.Update(textsMiddle[i]);
                viewCalculator.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            if (e.getSource() == jButtonsRight[i]) {
                viewCalculator.Update(textsRight[i]);
                viewCalculator.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsCursor.length; i++) {
            if (e.getSource() == jButtonsCursor[i]) {
                viewCalculator.Update(textsCursor[i]);
                viewCalculator.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsMode.length; i++) {
            if (e.getSource() == jButtonsMode[i]) {
                viewCalculator.SwitchMode();
                viewCalculator.UpdateView();
                return;
            }
        }
    }
}
