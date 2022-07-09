package Calculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class volumeSphere extends JRootPane {
    private javax.swing.JPanel jpanel1 = new javax.swing.JPanel();
    private javax.swing.JButton calculate = new javax.swing.JButton();
    private javax.swing.JTextField radiusTextField = new javax.swing.JTextField();
    private javax.swing.JLabel radius = new javax.swing.JLabel();
    private javax.swing.JLabel volume = new javax.swing.JLabel();
    private javax.swing.JLabel volumeCalculate = new javax.swing.JLabel();

    private javax.swing.JLabel spacer1 = new javax.swing.JLabel();

    public volumeSphere () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jpanel1.setLayout(new GridLayout(2,3));

        //jpanel1 = new javax.swing.JPanel();
        radius = new javax.swing.JLabel("Radius:");
        radiusTextField = new javax.swing.JTextField();
        calculate = new javax.swing.JButton("OK");
        volume = new javax.swing.JLabel("Volumen");
        volumeCalculate = new javax.swing.JLabel();
        spacer1 = new javax.swing.JLabel();
        //ggf. noch set Size
        jpanel1.add(radius);
        jpanel1.add(radiusTextField);
        jpanel1.add(calculate);
        jpanel1.add(volume);
        jpanel1.add(volumeCalculate);
        jpanel1.add(spacer1);
        this.getContentPane().add(jpanel1);
        //pack();
        setVisible(true);
        setSize(600,300);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Noch prüfen Buchstaben + leere Eingabe
                // Parse Double, da nicht möglich String direkt in Zahl umwandeln
                double volumeRadius = Double.parseDouble(radiusTextField.getText().replace(',','.'));
                if (volumeRadius <= 0) {
                    volumeCalculate.setText("ungültige Eingabe");
                }
                else {
                    double volumeResult = (4.0/3.0) * Math.PI * volumeRadius * volumeRadius * volumeRadius;
                    System.out.println(""+volumeResult);
                    double volume = (double) (Math.round(volumeResult * 100)) / 100;
                    String volumeString = (Double.toString(volume)).replace ('.', ',');
                    volumeCalculate.setText(volumeString);
                    //volumeCalculate.setText("" + (double) (Math.round(volumeResult * 100)) / 100);
                }
            }
        });

        //this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new volumeSphere().setVisible(true);
    }


}