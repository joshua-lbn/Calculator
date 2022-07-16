package Calculator.view.currency;

// Java-Imports
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * ProcessCurrencyInput-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche des Waehrungsrechners.
 * Programmtechnisch an die ViewCurrency-Klasse gegliedert.
 */
public class ProcessCurrencyInput implements DocumentListener, PropertyChangeListener {
    // Referenz auf die zugehoerige ViewCurrency-Instanz
    private ViewCurrency viewCurrency;

    /**
     * Konstruktor: Referenz auf zugehoerige ViewCurrency-Instanz setzen.
     * @param vc ViewCurrency-Instanz
     */
    public ProcessCurrencyInput(ViewCurrency vc) {
        viewCurrency = vc;
    }

    /**
     * Implementiere Methode, welche das Hinzufuegen von Text in das JTextField verarbeitet.
     * @param e Ereignis, welches verarbeitet wird
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        // Nur das JTextField kann dieses Ereignis ausloesen, daher keine Abfrage noetig
        viewCurrency.ConvertCurrency();
    }

    /**
     * Implementiere Methode, welche das Loeschen von Text in das JTextField verarbeitet.
     * @param e Ereignis, welches verarbeitet wird
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        // Nur das JTextField kann dieses Ereignis ausloesen, daher keine Abfrage noetig
        viewCurrency.ConvertCurrency();
    }

    /**
     * Implementiere Methode, welche das Aendern von Text in das JTextField verarbeitet.
     * @param e Ereignis, welches verarbeitet wird
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        // Nur das JTextField kann dieses Ereignis ausloesen, daher keine Abfrage noetig
        viewCurrency.ConvertCurrency();
    }

    /**
     * Implementiere Methode, welche die Auswahlaenderung in den JComboBoxes verarbeitet.
     * @param evt Ereignis, welches verarbeitet wird
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Nur die JComboBoxes koennen dieses Ereignis ausloesen, daher keine Abfrage noetig
        viewCurrency.ConvertCurrency();
    }
}