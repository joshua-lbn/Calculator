package Calculator.view.numeralSystem;

// Java-Imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Imports anderer Programmklassen
import Calculator.controller.Controller;

/**
 * ViewNumeralSystem-Klasse mit grafischer Benutzeroberflaeche des Zahlensystemsrechners.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewNumeralSystem extends JRootPane {
    // Referenz auf Controller-Instanz
    Controller controller;
    // Hauptpanel mit der ganzen Oberflaeche
    private javax.swing.JPanel jPanel;
    // Knoepfe
    private javax.swing.JButton convertDec;
    private javax.swing.JButton convertHex;
    private javax.swing.JButton convertBin;
    // Textfelder
    private javax.swing.JTextField decTextField;
    private javax.swing.JTextField hexaTextField;
    private javax.swing.JTextField binTextField;
    // Labels mit angezeigtem Text
    private javax.swing.JLabel decLabel;
    private javax.swing.JLabel hexaLabel;
    private javax.swing.JLabel binLabel;

    /**
     * Konstruktor: Referenz auf Controller-Instanz setzen, Oberflaeche erstellen und Eingabeverarbeitungs-
     * Instanzen hinzufuegen.
     * @param c Controller-Instanz
     */
    public ViewNumeralSystem (Controller c) {
        // Referenz auf Controller-Instanz setzen
        controller = c;
        // Panel erstellen und Layout setzen
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 3));
        // Restliche Elemente erstellen
        decLabel = new javax.swing.JLabel("   Dezimal:");
        decTextField = new javax.swing.JTextField();
        hexaLabel = new javax.swing.JLabel("   Hexadezimal:");
        hexaTextField = new javax.swing.JTextField();
        binLabel = new javax.swing.JLabel("   Bin\u00E4r:");
        binTextField = new javax.swing.JTextField();
        convertDec = new javax.swing.JButton("=");
        convertHex = new javax.swing.JButton("=");
        convertBin = new javax.swing.JButton("=");
        // Alle Elemente zum Panel hinzufuegen
        jPanel.add(decLabel);
        jPanel.add(decTextField);
        jPanel.add(convertDec);
        jPanel.add(hexaLabel);
        jPanel.add(hexaTextField);
        jPanel.add(convertHex);
        jPanel.add(binLabel);
        jPanel.add(binTextField);
        jPanel.add(convertBin);
        // Panel in ContentPane hinzufuegen
        this.getContentPane().add(jPanel);
        
        // ButtonListener als anonyme Subklassen hinzufuegen
        convertDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Feld zum Speichern des übergegeben Feldes
                String[] ConvertedNumbers = new String[2];
                //Uebergabe des Wertes an den controller
                ConvertedNumbers = controller.DectoHex_Bin(decTextField.getText().replace(',', '.'));
                //Ausgabe des Feldes
                if (ConvertedNumbers[0] == "Fehler") {
                    decTextField.setText("Ung\u00FCltige Eingabe");
                } else {
                    hexaTextField.setText(ConvertedNumbers[0]);
                    binTextField.setText(ConvertedNumbers[1]);
                }
            }
        });
        convertHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String HexString = hexaTextField.getText();
                String ConvertedNumbers[] = new String[2];
                ConvertedNumbers = controller.HextoDec_Bin(HexString);
                decTextField.setText(ConvertedNumbers[0]);
                binTextField.setText(ConvertedNumbers[1]);
            }
        });
        convertBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String BinString = binTextField.getText();
                String ConvertedNumbers[] = new String[2];
                ConvertedNumbers = controller.BintoDec_Hex(BinString);
                if (ConvertedNumbers[0] == "Fehler") {
                    binTextField.setText("Ung\u00FCltige Eingabe");
                } else {
                    decTextField.setText(ConvertedNumbers[0]);
                    hexaTextField.setText(ConvertedNumbers[1]);
                }
            }
        });
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        jPanel.setBackground(Color.white);
        decLabel.setForeground(Color.black);
        decLabel.setBackground(Color.white);
        hexaLabel.setForeground(Color.black);
        hexaLabel.setBackground(Color.white);
        binLabel.setForeground(Color.black);
        binLabel.setBackground(Color.white);
        decTextField.setForeground(Color.black);
        decTextField.setBackground(Color.white);
        hexaTextField.setForeground(Color.black);
        hexaTextField.setBackground(Color.white);
        binTextField.setForeground(Color.black);
        binTextField.setBackground(Color.white);
        convertDec.setForeground(Color.black);
        convertDec.setBackground(Color.white);
        convertHex.setForeground(Color.black);
        convertHex.setBackground(Color.white);
        convertBin.setForeground(Color.black);
        convertBin.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu setzen.
     */
    public void SetDarkmode() {
        jPanel.setBackground(Color.black);
        decLabel.setForeground(Color.white);
        decLabel.setBackground(Color.black);
        hexaLabel.setForeground(Color.white);
        hexaLabel.setBackground(Color.black);
        binLabel.setForeground(Color.white);
        binLabel.setBackground(Color.black);
        decTextField.setForeground(Color.white);
        decTextField.setBackground(Color.black);
        hexaTextField.setForeground(Color.white);
        hexaTextField.setBackground(Color.black);
        binTextField.setForeground(Color.white);
        binTextField.setBackground(Color.black);
        convertDec.setForeground(Color.white);
        convertDec.setBackground(Color.black);
        convertHex.setForeground(Color.white);
        convertHex.setBackground(Color.black);
        convertBin.setForeground(Color.white);
        convertBin.setBackground(Color.black);
    }
}