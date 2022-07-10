package Calculator.view.calculator;

import java.awt.event.ActionEvent;

/*
 * ProcessButtonInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche des Taschenrechners.
 * Programmtechnisch an die ViewCalculator-Klasse gegliedert.
 */
public class ProcessButtonInput implements java.awt.event.ActionListener {
    // Referenz auf Oberklasse
    private ViewCalculator viewCalculator;

    /**
     * Konstruktor: übergebene ViewCalculator-Instanz speichern und lokale Attribute aus derselben erhalten.
     * @param vC ViewCalculator-Instanz
     */
    public ProcessButtonInput(ViewCalculator vC) {
        // View speichern
        viewCalculator = vC;
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
         * der viewCalculator der Befehl gegeben, die Tastatureingabe zu ermoeglichen.
         * die weitere Ausfuehrung der Methode aus Effizienzgruenden abgebrochen.
         */
        for (int i = 0; i < viewCalculator.GetJButtonsNumber().length; i++) {
            if (e.getSource() == viewCalculator.GetJButtonsNumber()[i]) {
                viewCalculator.Update("" + (i) + "");
                viewCalculator.UpdateView();
                viewCalculator.UpdateFocus();
                return;
            }
        }
        for (int i = 0; i < viewCalculator.GetJButtonsLeft().length; i++) {
            if (e.getSource() == viewCalculator.GetJButtonsLeft()[i]) {
                viewCalculator.Update(viewCalculator.GetTextsLeft()[i]);
                viewCalculator.UpdateView();
                viewCalculator.UpdateFocus();
                return;
            }
        }
        for (int i = 0; i < viewCalculator.GetJButtonsMiddle().length; i++) {
            if (e.getSource() == viewCalculator.GetJButtonsMiddle()[i]) {
                viewCalculator.Update(viewCalculator.GetTextsMiddle()[i]);
                viewCalculator.UpdateView();
                viewCalculator.UpdateFocus();
                return;
            }
        }
        for (int i = 0; i < viewCalculator.GetJButtonsRight().length; i++) {
            if (e.getSource() == viewCalculator.GetJButtonsRight()[i]) {
                viewCalculator.Update(viewCalculator.GetTextsRight()[i]);
                viewCalculator.UpdateView();
                viewCalculator.UpdateFocus();
                return;
            }
        }
    }
}