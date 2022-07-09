package Calculator.view.viewGeneral;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
     * Methode, um auf Aenderungen der Checkboxes zu reagieren.
     * @param e Das Ereignis bzw. "Event"
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        // Soweit Zustand der Darkmode-Checkbox geaendert
        if (e.getSource() == viewSettings.GetDarkmodeSetting()) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                viewSettings.ActivatedDarkmodeSetting();
            } else {
                viewSettings.DeactivatedDarkmodeSetting();
            }
        }
    }
}
