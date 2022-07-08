package Calculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class volumeCuboid extends JRootPane {
    private javax.swing.JPanel jpanel1 = new javax.swing.JPanel();
    private javax.swing.JButton calculate = new javax.swing.JButton();
    private javax.swing.JTextField lengthTextField = new javax.swing.JTextField();
    private javax.swing.JTextField widthTextField = new javax.swing.JTextField();
    private javax.swing.JTextField heightTextField = new javax.swing.JTextField();
    private javax.swing.JLabel length = new javax.swing.JLabel();
    private javax.swing.JLabel width = new javax.swing.JLabel();
    private javax.swing.JLabel height = new javax.swing.JLabel();
    private javax.swing.JLabel volume = new javax.swing.JLabel();
    private javax.swing.JLabel volumeCalculate = new javax.swing.JLabel();

    private javax.swing.JLabel spacer1 = new javax.swing.JLabel();
    private javax.swing.JLabel spacer2 = new javax.swing.JLabel();
    private javax.swing.JLabel spacer3 = new javax.swing.JLabel();

    public volumeCuboid () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jpanel1.setLayout(new GridLayout(4,3));

        //jpanel1 = new javax.swing.JPanel();
        length = new javax.swing.JLabel("Länge:");
        lengthTextField = new javax.swing.JTextField();
        spacer1 = new javax.swing.JLabel();
        width = new javax.swing.JLabel("Breite:");
        widthTextField = new javax.swing.JTextField();
        spacer2 = new javax.swing.JLabel();
        height = new javax.swing.JLabel("Höhe:");
        heightTextField = new javax.swing.JTextField();
        calculate = new javax.swing.JButton("OK");
        volume = new javax.swing.JLabel("Volumen");
        volumeCalculate = new javax.swing.JLabel();
        spacer3 = new javax.swing.JLabel();
        //ggf. noch set Size
        jpanel1.add(length);
        jpanel1.add(lengthTextField);
        jpanel1.add(spacer1);
        jpanel1.add(width);
        jpanel1.add(widthTextField);
        jpanel1.add(spacer2);
        jpanel1.add(height);
        jpanel1.add(heightTextField);
        jpanel1.add(calculate);
        jpanel1.add(volume);
        jpanel1.add(volumeCalculate);
        jpanel1.add(spacer3);
        this.getContentPane().add(jpanel1);
        //pack();
        setVisible(true);
        setSize(600,300);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Noch prüfen Buchstaben + leere Eingabe
                // Parse Double, da nicht möglich String direkt in Zahl umwandeln
                double volumeLength = Double.parseDouble(lengthTextField.getText());
                double volumeWidth = Double.parseDouble(widthTextField.getText());
                double volumeHeight = Double.parseDouble(heightTextField.getText());
                if (volumeLength <= 0 || volumeWidth <= 0 || volumeHeight <= 0) {
                    volumeCalculate.setText("ungültige Eingabe");
                }
                else {
                    double volumeResult = volumeLength * volumeWidth * volumeHeight;
                    double volume = (double) ((Math.round(volumeResult * 100)) / 100);
                    Double.toString(volume).replace (',', '.');
                    volumeCalculate.setText("" + (double) (Math.round(volumeResult * 100)) / 100);
                }
            }
        });

        //this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new volumeCuboid().setVisible(true);
    }


}