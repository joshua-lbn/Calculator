package Calculator.view.main;

// Java-Imports
import java.awt.event.ActionListener;

/*
 * ProcessMenuInput-Klasse zum Auswerten der Menue-Interaktionen mit der grafischen Hauptoberflaeche.
 * Programmtechnisch an die View-Klasse gegliedert.
 */
public class ProcessMenuInput implements ActionListener {
    // Referenz auf die View-Instanz
    private View view;

    /**
     * Konstruktor: uebergebene View-Instanz speichern.
     * @param v View-Instanz
     */
    public ProcessMenuInput(View v) {
        view = v;
    }

    /**
     * Methode, welche bei Druecken auf Menue-Eintraege aufgerufen wird.
     * @param e Das "Event" bzw. Ereignis, welches verarbeitet werden soll
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == view.GetJMenuItemSettings()) {
            // Die Anzeige ueber Methoden in der View aktualisieren
            view.SetSettings();
        }
        else if (e.getSource() == view.GetJMenuItemHelp()) {
            view.SetHelp();
        }
        else if (e.getSource() == view.GetJMenuItemCalculator()) {
            view.SetCalculator();
        }
        else if (e.getSource() == view.GetJMenuItemCone()) {
            view.SetVolume(1);
        }
        else if (e.getSource() == view.GetJMenuItemCuboid()) {
            view.SetVolume(2);
        }
        else if (e.getSource() == view.GetJMenuItemCylinder()) {
            view.SetVolume(3);
        }
        else if (e.getSource() == view.GetJMenuItemSphere()) {
            view.SetVolume(4);
        }
        else if (e.getSource() == view.GetJMenuItemOpenNumeralSystem()) {
            view.SetNumeralSystem();
        }
        else if (e.getSource() == view.GetJMenuItemCurrency()) {
            view.SetCurrency();
        }
    }
}
