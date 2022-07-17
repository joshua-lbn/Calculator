package Calculator.view.miscellaneous;

// Java-Imports
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/*
 * ProcessCheckboxes-Klasse zum Auswerten der Interaktionen mit der grafischen Oberflaeche der Einstellungen.
 * Programmtechnisch an die ViewSettings-Klasse gegliedert.
 */
public class ProcessCheckboxes implements ItemListener {
    // Referenz auf die ViewSettings-Instanz
    ViewSettings viewSettings;

    /**
     * Konstruktor: ViewSettings-Instanz setzen.
     * @param vS ViewSettings-Instanz
     */
    public ProcessCheckboxes(ViewSettings vS) {
        viewSettings = vS;
    }

    /**
     * Methode, um auf Aenderungen der Checkbox zu reagieren.
     * @param e Das Ereignis bzw. "Event"
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        // Soweit Zustand der Checkbox bezueglich des dunklen Modus geaendert
        if (e.getSource() == viewSettings.GetDarkmodeSetting()) {
            // Falls aktiviert: dunklen Modus ueber ViewSettings-Instanz global aktivieren
            if (e.getStateChange() == ItemEvent.SELECTED) {
                viewSettings.ActivateDarkmodeSetting();
            // Falls deaktiviert: dunklen Modus ueber ViewSettings-Instanz global deaktivieren
            } else {
                viewSettings.DeactivateDarkmodeSetting();
            }
        }
    }
}
