package Calculator.view;

import javax.swing.*;

/**
 * Klasse mit der Darstellung des Zahlensystemsumrechners.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewNumeralSystem extends JFrame {
    // Hauptpanel
    private JPanel numeralMainPanel;
    // Textfelder mit Ein- bzw. Ausgabe
    private JTextField decimalTextfield;
    private JTextField hexaTextField;
    private JTextField binaryTextField;
    // Knoepfe
    private JButton button1;
    private JButton button2;
    private JButton convertButton;

    /**
     * Konstruktor: Initialisierung der Oberflaeche.
     */
    public ViewNumeralSystem() {
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hinzufuegen des Hauptpanels in das Fenster
        this.setContentPane(numeralMainPanel);
        // Fenstergroesse aenderbar machen
        this.pack();
        // Sichtbar setzen
        this.setVisible(true);
        }

    // Uebergangsweise zum Testen: main-Methode zum Ausfuehren
    public static void main (String[] args)
    {
        JFrame frame = new ViewNumeralSystem();
        frame.setVisible(true);
    }
}