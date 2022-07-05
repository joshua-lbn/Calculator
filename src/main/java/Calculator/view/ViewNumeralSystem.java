package Calculator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNumeralSystem extends JRootPane {
    //Knoepfe
    private JButton convertDec;
    private JButton convertHex;
    private JButton convertBin;
    //Textfelder
    private JTextField DecTextField;
    private JTextField HexaTextField;
    private JTextField BinTextField;
    //Label
    private JLabel DecLabel;
    private JLabel HexaLabel;
    private JLabel BinLabel;
    //Hauptpanel
    private JPanel NumeralViewPanel;
    /**
     * Konstruktor: Initialisierung der Oberflaeche.
     */
    public ViewNumeralSystem() {
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hinzufuegen des Hauptpanels in das Fenster
        this.setContentPane(NumeralViewPanel);
        // Fenstergroesse aenderbar machen
        // this.pack();
        // Sichtbar setzen
        // this.setVisible(true);
        convertDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double decimalNumber = Double.parseDouble(DecTextField.getText());
                BinTextField.setText("Test erfolgreich");
                HexaTextField.setText("Test erfolgreich");
                //Hier Methode zum umrechnen
            }
        });
    }
    // Uebergangsweise zum Testen: main-Methode zum Ausfuehren
    //public static void main (String[] args)
    //{
    //    JFrame frame = new ViewNumeralSystem();
    //    frame.setVisible(true);
    //}
}

