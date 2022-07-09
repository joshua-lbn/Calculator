package Calculator.view.viewGeneral;

import Calculator.view.main.View;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    // File und BufferedWriter, um Einstellungen zu speichern
    private File file;
    private File folder;
    private BufferedWriter bufferedWriter;

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
         * 1. Panel erstellen
         * 2. Titel erstellen
         * 3. Darkmode-Checkbox erstellen
         * 5. Zusammenfuegen
         */
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        explanationsLabel = new JLabel(" Dies sind die Einstellungen des Taschenrechners.");
        panel.add(explanationsLabel);
        darkmodeSetting = new JCheckBox("Dunkler Modus deaktiviert");
        darkmodeSetting.addItemListener(processCheckboxes);
        panel.add(darkmodeSetting);
        this.getContentPane().add(panel);
        // Instanzen von File und BufferedWriter
        folder = new File(System.getenv("APPDATA") + "\\Calculator");
        file = new File (System.getenv("APPDATA") + "\\Calculator\\settings.txt");
    }

    /**
     * Methode, um den Text der JCheckBox darkmodeSetting auf "aktiviert" zu setzen und Aenderung an die View-Instanz
     * weiterzugeben.
     */
    public void ActivatedDarkmodeSetting() {
        darkmodeSetting.setText("Dunkler Modus aktiviert");
        darkmodeSetting.setSelected(true);
        view.SetDarkmode();
        try {
            folder.mkdir();
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("Darkmode: on");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Methode, um den Text der JCheckBox darkmodeSetting auf "aktiviert" zu setzen und Aenderung an die View-Instanz
     * weiterzugeben.
     */
    public void DeactivatedDarkmodeSetting() {
        darkmodeSetting.setText("Dunkler Modus deaktiviert");
        darkmodeSetting.setSelected(false);
        view.SetLightmode();
        try {
            folder.mkdir();
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("Darkmode: off");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
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
