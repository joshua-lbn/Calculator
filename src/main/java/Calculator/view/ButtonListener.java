package Calculator.view;

import javax.swing.*;

/*
 * ButtonListener-Klasse zum Auswerden der Interaktionen mit der grafischen Oberflaeche.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ButtonListener implements java.awt.event.ActionListener {
    private View view;
    // Relevante Attribute: Arrays mit Knopfobjekten und Beschriftungen
    private JButton[] jButtonsNumber;
    private JButton[] jButtonsLeft;
    private String[] textsLeft;
    private JButton[] jButtonsMiddle;
    private String[] textsMiddle;
    private JButton[] jButtonsRight;
    private String[] textsRight;

    /**
     * Konstruktor, der die übergebene View-Instanz speichert und die lokalen Attribute aus der View ueber Getter erhaelt.
     * @param v View-Instanz
     */
    public ButtonListener(View v) {
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
    }

    /**
     * Methode, welche bei Knopfdrucken aufgerufen wird.
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
                view.Update(textsLeft[i].toString());
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsMiddle.length; i++) {
            if (e.getSource() == jButtonsMiddle[i]) {
                view.Update(textsMiddle[i].toString());
                view.UpdateView();
                return;
            }
        }
        for (int i = 0; i < jButtonsRight.length; i++) {
            if (e.getSource() == jButtonsRight[i]) {
                view.Update(textsRight[i].toString());
                view.UpdateView();
                return;
            }
        }
    }
}