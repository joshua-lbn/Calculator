package Calculator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasse mit der Darstellung des Quaderrechners.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewVolumeSquare extends JRootPane {
    // Hautpanel
    private JPanel mainPanel;
    // Textfelder zur Eingabe
    private JTextField lengthTextField;
    private JTextField widthTextField;
    private JTextField heightTextField;
    // Ungenutzt?
    private JPanel lengthJPanel;
    private JLabel length;
    private JPanel widthJPanel;
    private JLabel width;
    private JPanel heightJPanel;
    private JLabel height;
    private JPanel volumeJPanel;
    private JLabel volume;
    // Knopf zur Berechnung
    private JButton calculate;
    // Panel und Label mit Ausgabe
    private JPanel volumeOutput;
    private JLabel volumeOutputSquare;

    /**
     * Konstruktor: Oberflaeche initialisieren.
     */
    public ViewVolumeSquare() {
        // Schliessoperation setzen
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Hauptpanel hinzufuegen
        this.setContentPane(mainPanel);
        // Fenstergroesse aenderbar setzen
        //this.pack();
        // Sichtbar setzen
        //this.setVisible(true);
        // Uebergangsweise: kommt in eigene Klasse
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Noch prüfen Buchstaben + leere Eingabe
                // Parse Double, da nicht möglich String direkt in Zahl umwandeln
                double volumeLength = Double.parseDouble(lengthTextField.getText());
                double volumeWidth = Double.parseDouble(widthTextField.getText());
                double volumeHeight = Double.parseDouble(heightTextField.getText());
                if (volumeLength <= 0 || volumeWidth <= 0 || volumeHeight <= 0) {
                    volumeOutputSquare.setText("ungültige Eingabe");
                }
                else {
                    double volumeResult = volumeLength * volumeWidth * volumeHeight;
                    System.out.println((double) (Math.round(volumeResult * 100)) / 100);
                    volumeOutputSquare.setText("" + (double) (Math.round(volumeResult * 100)) / 100);
                }
            }
        });
    }

    // Uebergangsweise: zum Testen
    //public static void main(String[] args) {
    //        JFrame frame = new ViewVolumeSquare();
    //        frame.setVisible(true);
    //}
}

