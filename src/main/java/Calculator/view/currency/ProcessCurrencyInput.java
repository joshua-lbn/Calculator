package Calculator.view.currency;

// Java-Imports
import java.awt.event.ActionListener;

/*
 * ProcessCurrencyInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche des Waehrungsrechners.
 * Programmtechnisch an die ViewCurrency-Klasse gegliedert.
 */
public class ProcessCurrencyInput implements ActionListener {
    // Referenz auf die zugehoerige ViewCurrency-Instanz
    private ViewCurrency viewCurrency;
    // Verwendete Werte
    double input;
    double output;

    /**
     * Konstruktor: Referenz auf zugehoerige ViewCurrency-Instanz setzen.
     * @param vc ViewCurrency-Instanz
     */
    public ProcessCurrencyInput(ViewCurrency vc) {
        viewCurrency = vc;
    }

    /**
     * Methode, welche den Knopfdruck verarbeitet.
     * @param e Zu verarbeitendes Ereignis
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Eingabe aus Feld in Double umwandeln
        input = Double.parseDouble(viewCurrency.GetTextInput().getText());
        // Ausgabe in Controller (ueber ViewCurrency- und dann View-Instanz) berechnen
        output = viewCurrency.ConvertCurrency(input);
        // Ergebnis im Fenster anzeigen
        viewCurrency.SetResult(output);
    }
}