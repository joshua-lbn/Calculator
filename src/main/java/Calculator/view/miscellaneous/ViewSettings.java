package Calculator.view.miscellaneous;

//Java-Imports
import javax.swing.*;
import java.awt.*;
// Imports anderer Programmklassen
import Calculator.view.main.View;

/**
 * ViewSettings-Klasse mit Einstellungs-Unterfenster.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewSettings extends JRootPane {
    // Referenz auf View
    private View view;
    // Elemente der Oberflaeche
    private JPanel panel;
    private JLabel explanationsLabel;
    private JCheckBox darkmodeSetting;
    // ItemListener fuer die Checkbox
    private ProcessCheckboxes processCheckboxes;

    /**
     * Konstruktor: View-Referenz setzen und Oberflaeche initialisieren, dabei Listener hinzufuegen.
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
         * 4. der Listener fuer Aenderungen der Checkbox instanziiert wird
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
     * Getter-Methode fuer JCheckBox darkmodeSetting.
     * @return JCheckBox darkmodeSetting
     */
    protected JCheckBox GetDarkmodeSetting() {
        return darkmodeSetting;
    }

    /**
     * Methode, um den dunklen Modus global zu aktivieren.
     */
    public void ActivateDarkmodeSetting() {
        // JCheckBox aendern
        darkmodeSetting.setText("Dunkler Modus aktiviert");
        darkmodeSetting.setSelected(true);
        // Alle Darstellungen ueber View aendern
        view.SetDarkmode();
        // Speichern im Dateisystem
        view.SaveDarkmode(true);
    }

    /**
     * Methode, um den dunklen Modus global zu deaktivieren.
     */
    public void DeactivateDarkmodeSetting() {
        // JCheckBox aendern
        darkmodeSetting.setText("Dunkler Modus deaktiviert");
        darkmodeSetting.setSelected(false);
        // Alle Darstellungen ueber View aendern
        view.SetLightmode();
        // Speichern im Dateisystem
        view.SaveDarkmode(false);
    }

    /**
     * Methode, um den hellen Modus in diesem Unterfenster zu aktivieren.
     */
    public void SetLightmode() {
        panel.setBackground(Color.white);
        explanationsLabel.setForeground(Color.black);
        explanationsLabel.setBackground(Color.white);
        darkmodeSetting.setForeground(Color.black);
        darkmodeSetting.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus in diesem Unterfenster zu aktivieren.
     */
    public void SetDarkmode() {
        panel.setBackground(Color.black);
        explanationsLabel.setForeground(Color.white);
        explanationsLabel.setBackground(Color.black);
        darkmodeSetting.setForeground(Color.white);
        darkmodeSetting.setBackground(Color.black);
    }
}
