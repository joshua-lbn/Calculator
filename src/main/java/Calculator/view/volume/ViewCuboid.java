package Calculator.view.volume;

// Java-Imports
import javax.swing.*;
import java.awt.*;
// Imports anderer Klassen
import Calculator.controller.Controller;

/**
 * ViewCuboid-Klasse mit grafischer Benutzeroberflaeche des Quader-Volumenberechnungs-Unterfenster.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewCuboid extends JRootPane implements ViewVolume{
    // Elemente der Oberflaeche
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton calculate;
    private javax.swing.JTextField lengthTextField;
    private javax.swing.JTextField widthTextField;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JLabel length;
    private javax.swing.JLabel width;
    private javax.swing.JLabel height;
    private javax.swing.JLabel volume;
    private javax.swing.JLabel volumeCalculate;
    private javax.swing.JLabel spacer1;
    private javax.swing.JLabel spacer2;
    private javax.swing.JLabel spacer3;
    // Instanz der Beiklasse "ProcessVolumeInput", um auf Drucke auf den Knopf zu reagieren
    private ProcessVolumeInput processVolumeInput;

    /**
     * Konstruktor: Oberflaeche erstellen und Eingabeverarbeitungsinstanz hinzufuegen.
     * @param c Controller-Instanz
     */
    public ViewCuboid(Controller c) {
        // Eingabeverarbeitung instanziieren und Controller uebergeben
        processVolumeInput = new ProcessVolumeInput(this, c);
        // Panel erstellen und Layout setzen
        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(4,3));
        // Weitere Elemente der Oberflaeche erstellen
        length = new javax.swing.JLabel("   Länge:");
        lengthTextField = new javax.swing.JTextField();
        spacer1 = new javax.swing.JLabel();
        width = new javax.swing.JLabel("    Breite:");
        widthTextField = new javax.swing.JTextField();
        spacer2 = new javax.swing.JLabel();
        height = new javax.swing.JLabel("   Höhe:");
        heightTextField = new javax.swing.JTextField();
        calculate = new javax.swing.JButton("OK");
        volume = new javax.swing.JLabel("   Volumen");
        volumeCalculate = new javax.swing.JLabel();
        spacer3 = new javax.swing.JLabel();
        // Elemente in Panel einfuegen
        jPanel1.add(length);
        jPanel1.add(lengthTextField);
        jPanel1.add(spacer1);
        jPanel1.add(width);
        jPanel1.add(widthTextField);
        jPanel1.add(spacer2);
        jPanel1.add(height);
        jPanel1.add(heightTextField);
        jPanel1.add(calculate);
        jPanel1.add(volume);
        jPanel1.add(volumeCalculate);
        jPanel1.add(spacer3);
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
     * Getter-Methode fuer den Text des Laenge-Textfeldes.
     * @return Eingabe des Laenge-Textfeldes als String
     */
    public String GetTextLength() {
        return lengthTextField.getText();
    }

    /**
     * Getter-Methode fuer den Text des Breite-Textfeldes.
     * @return Eingabe des Breite-Textfeldes als String
     */
    public String GetTextWidth() {
        return widthTextField.getText();
    }

    /**
     * Getter-Methode fuer den Text des Hoehe-Textfeldes.
     * @return Eingabe des Hoehe-Textfeldes als String
     */
    public String GetTextHeight() {
        return heightTextField.getText();
    }

    /**
     * Methode, um den hellen Modus zu aktivieren.
     */
    public void SetLightmode() {
        jPanel1.setBackground(Color.white);
        calculate.setForeground(Color.black);
        calculate.setBackground(Color.white);
        lengthTextField.setForeground(Color.black);
        lengthTextField.setBackground(Color.white);
        heightTextField.setForeground(Color.black);
        heightTextField.setBackground(Color.white);
        widthTextField.setForeground(Color.black);
        widthTextField.setBackground(Color.white);
        length.setForeground(Color.black);
        length.setBackground(Color.white);
        height.setForeground(Color.black);
        height.setBackground(Color.white);
        width.setForeground(Color.black);
        width.setBackground(Color.white);
        volume.setForeground(Color.black);
        volume.setBackground(Color.white);
        volumeCalculate.setForeground(Color.black);
        volumeCalculate.setBackground(Color.white);
        spacer1.setForeground(Color.black);
        spacer1.setBackground(Color.white);
        spacer2.setForeground(Color.black);
        spacer2.setBackground(Color.white);
        spacer3.setForeground(Color.black);
        spacer3.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu aktivieren.
     */
    public void SetDarkmode() {
        jPanel1.setBackground(Color.black);
        calculate.setForeground(Color.white);
        calculate.setBackground(Color.black);
        lengthTextField.setForeground(Color.white);
        lengthTextField.setBackground(Color.black);
        heightTextField.setForeground(Color.white);
        heightTextField.setBackground(Color.black);
        widthTextField.setForeground(Color.white);
        widthTextField.setBackground(Color.black);
        length.setForeground(Color.white);
        length.setBackground(Color.black);
        height.setForeground(Color.white);
        height.setBackground(Color.black);
        width.setForeground(Color.white);
        width.setBackground(Color.black);
        volume.setForeground(Color.white);
        volume.setBackground(Color.black);
        volumeCalculate.setForeground(Color.white);
        volumeCalculate.setBackground(Color.black);
        spacer1.setForeground(Color.white);
        spacer1.setBackground(Color.black);
        spacer2.setForeground(Color.white);
        spacer2.setBackground(Color.black);
        spacer3.setForeground(Color.black);
        spacer3.setBackground(Color.white);
    }
}
