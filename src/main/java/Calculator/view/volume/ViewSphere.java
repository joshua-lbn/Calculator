package Calculator.view.volume;

// Java-Imports
import javax.swing.*;
import java.awt.*;
// Imports anderer Klassen
import Calculator.controller.Controller;

/**
 * ViewSphere-Klasse mit grafischer Benutzeroberflaeche des Kugel-Volumenberechnungs-Unterfenster.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewSphere extends JRootPane implements ViewVolume{
    // Elemente der Oberflaeche
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton calculate;
    private javax.swing.JTextField radiusTextField;
    private javax.swing.JLabel radius;
    private javax.swing.JLabel volume;
    private javax.swing.JLabel volumeCalculate;
    private javax.swing.JLabel spacer1;
    // Instanz der Beiklasse "ProcessVolumeInput", um auf Drucke auf den Knopf zu reagieren
    private ProcessVolumeInput processVolumeInput;

    /**
     * Konstruktor: Oberflaeche erstellen und Eingabeverarbeitungsinstanz hinzufuegen.
     * @param c Controller-Instanz
     */
    public ViewSphere(Controller c) {
        // Eingabeverarbeitung instanziieren und Controller uebergeben
        processVolumeInput = new ProcessVolumeInput(this, c);
        // Panel erstellen und Layout setzen
        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2,3));
        // Weitere Elemente der Oberflaeche erstellen
        radius = new javax.swing.JLabel("   Radius:");
        radiusTextField = new javax.swing.JTextField();
        calculate = new javax.swing.JButton("OK");
        volume = new javax.swing.JLabel("   Volumen");
        volumeCalculate = new javax.swing.JLabel();
        spacer1 = new javax.swing.JLabel();
        // Elemente in Panel einfuegen
        jPanel1.add(radius);
        jPanel1.add(radiusTextField);
        jPanel1.add(calculate);
        jPanel1.add(volume);
        jPanel1.add(volumeCalculate);
        jPanel1.add(spacer1);
        // Panel in ContentPane einfuegen
        this.getContentPane().add(jPanel1);
        // Eingabeverarbeitung hinzufuegen
        calculate.addActionListener(processVolumeInput);
    }

    /**
     * Methode, um das Ergebnis der Berechnung zu setzen.
     * @param volume Ergebnis als String
     */
    public void VolumeOutput(String volume) {
        volumeCalculate.setText(volume);
    }

    /**
     * Getter-Methode fuer den JButton getCalculate.
     * @return JButton getCalculate
     */
    public JButton getCalculate() {
        return calculate;
    }

    /**
     * Getter-Methode fuer den Text des Radius-Textfeldes.
     * @return Eingabe des Radius-Textfeldes als String
     */
    public String GetTextRadius() {
        return radiusTextField.getText();
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        jPanel1.setBackground(Color.white);
        calculate.setForeground(Color.black);
        calculate.setBackground(Color.white);
        radiusTextField.setForeground(Color.black);
        radiusTextField.setBackground(Color.white);
        radius.setForeground(Color.black);
        radius.setBackground(Color.white);
        volume.setForeground(Color.black);
        volume.setBackground(Color.white);
        volumeCalculate.setForeground(Color.black);
        volumeCalculate.setBackground(Color.white);
        spacer1.setForeground(Color.black);
        spacer1.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        jPanel1.setBackground(Color.black);
        calculate.setForeground(Color.white);
        calculate.setBackground(Color.black);
        radiusTextField.setForeground(Color.white);
        radiusTextField.setBackground(Color.black);
        radius.setForeground(Color.white);
        radius.setBackground(Color.black);
        volume.setForeground(Color.white);
        volume.setBackground(Color.black);
        volumeCalculate.setForeground(Color.white);
        volumeCalculate.setBackground(Color.black);
        spacer1.setForeground(Color.white);
        spacer1.setBackground(Color.black);
    }
}
