package Calculator.view.miscellaneous;

import Calculator.view.main.View;

import javax.swing.*;
import java.awt.*;

/**
 * ViewSettings-Klasse mit Einstellungsoberflaeche.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewSettings extends JRootPane {
    // Referenz auf View
    private View view;
    // Noetige Elemente des Fensters
    private JPanel panel;
    private JLabel explanationsLabel;
    private JCheckBox darkmodeSetting;
    // ItemListener fuer die Checkboxes
    private ProcessCheckboxes processCheckboxes;

    /**
     * Konstruktor: View-Referenz setzen und Oberflaeche initialisieren.
     * @param v View-Referenz
     */
    public ViewSettings(View v) {
        // View-Referenz setzen
        view = v;
        // ItemListener kreieren
        processCheckboxes = new ProcessCheckboxes(this);
        /* Oberflaeche erstellen, indem
         * 1. das Panel erstellt wird
         * 2. der Titel erzeugt wird
         * 3. die Darkmode-Checkbox kreiert wird
         * 5. alles zusammengefuert wird
         */
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        explanationsLabel = new JLabel(" Dies sind die Einstellungen des Taschenrechners.");
        panel.add(explanationsLabel);
        darkmodeSetting = new JCheckBox("Dunkler Modus deaktiviert");
        darkmodeSetting.addItemListener(processCheckboxes);
        panel.add(darkmodeSetting);
        this.getContentPane().add(panel);
    }

    /**
     * Methode, um den Text der JCheckBox darkmodeSetting auf "aktiviert" zu setzen, Aenderung an die View-Instanz
     * weiterzugeben und ueber Model zu speichern.
     */
    public void ActivateDarkmodeSetting() {
        darkmodeSetting.setText("Dunkler Modus aktiviert");
        darkmodeSetting.setSelected(true);
        view.SetDarkmode();
        view.SaveDarkmode(true);
    }

    /**
     * Methode, um den Text der JCheckBox darkmodeSetting auf "aktiviert" zu setzen, Aenderung an die View-Instanz
     * weiterzugeben und ueber Model zu speichern.
     */
    public void DeactivateDarkmodeSetting() {
        darkmodeSetting.setText("Dunkler Modus deaktiviert");
        darkmodeSetting.setSelected(false);
        view.SetLightmode();
        view.SaveDarkmode(false);
    }

    /**
     * Methode, um den hellen Modus im Unterfenster zu aktivieren.
     */
    public void SetLightmode() {
        panel.setBackground(Color.white);
        explanationsLabel.setForeground(Color.black);
        explanationsLabel.setBackground(Color.white);
        darkmodeSetting.setForeground(Color.black);
        darkmodeSetting.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus im Unterfenster zu aktivieren.
     */
    public void SetDarkmode() {
        panel.setBackground(Color.black);
        explanationsLabel.setForeground(Color.white);
        explanationsLabel.setBackground(Color.black);
        darkmodeSetting.setForeground(Color.white);
        darkmodeSetting.setBackground(Color.black);
    }

    /**
     * Getter-Methode fuer JCheckBox darkmodeSetting.
     * @return JCheckBox darkmodeSetting
     */
    public JCheckBox GetDarkmodeSetting() {
        return darkmodeSetting;
    }
}
