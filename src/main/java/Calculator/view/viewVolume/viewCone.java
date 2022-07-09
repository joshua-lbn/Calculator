package Calculator.view.viewVolume;

import Calculator.view.main.ProcessVolumeInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class viewCone extends JRootPane {
    private ProcessVolumeInput processVolumeInput;
    private javax.swing.JPanel jpanel1 = new javax.swing.JPanel();
    private javax.swing.JButton calculate = new javax.swing.JButton();
    private javax.swing.JTextField radiusTextField = new javax.swing.JTextField();
    private javax.swing.JTextField heightTextField = new javax.swing.JTextField();
    private javax.swing.JLabel radius = new javax.swing.JLabel();
    private javax.swing.JLabel height = new javax.swing.JLabel();
    private javax.swing.JLabel volume = new javax.swing.JLabel();
    private javax.swing.JLabel volumeCalculate = new javax.swing.JLabel();

    private javax.swing.JLabel spacer1 = new javax.swing.JLabel();
    private javax.swing.JLabel spacer2 = new javax.swing.JLabel();

    public viewCone () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jpanel1.setLayout(new GridLayout(3,3));

        //jpanel1 = new javax.swing.JPanel();
        radius = new javax.swing.JLabel("   Radius:");
        radiusTextField = new javax.swing.JTextField();
        spacer1 = new javax.swing.JLabel();
        height = new javax.swing.JLabel("   Höhe:");
        heightTextField = new javax.swing.JTextField();
        calculate = new javax.swing.JButton("OK");
        volume = new javax.swing.JLabel("   Volumen");
        volumeCalculate = new javax.swing.JLabel();
        spacer2 = new javax.swing.JLabel();
        //ggf. noch set Size
        jpanel1.add(radius);
        jpanel1.add(radiusTextField);
        jpanel1.add(spacer1);
        jpanel1.add(height);
        jpanel1.add(heightTextField);
        jpanel1.add(calculate);
        jpanel1.add(volume);
        jpanel1.add(volumeCalculate);
        jpanel1.add(spacer2);
        this.getContentPane().add(jpanel1);
        //pack();
        setVisible(true);
        setSize(600,300);
        calculate.addActionListener(processVolumeInput);

        //this.pack();
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Calculator.view.volumeCone().setVisible(true);
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        jpanel1.setBackground(Color.white);
        calculate.setForeground(Color.black);
        calculate.setBackground(Color.white);
        radiusTextField.setForeground(Color.black);
        radiusTextField.setBackground(Color.white);
        heightTextField.setForeground(Color.black);
        heightTextField.setBackground(Color.white);
        radius.setForeground(Color.black);
        radius.setBackground(Color.white);
        height.setForeground(Color.black);
        height.setBackground(Color.white);
        volume.setForeground(Color.black);
        volume.setBackground(Color.white);
        volumeCalculate.setForeground(Color.black);
        volumeCalculate.setBackground(Color.white);
        spacer1.setForeground(Color.black);
        spacer1.setBackground(Color.white);
        spacer2.setForeground(Color.black);
        spacer2.setBackground(Color.white);
    }

    /**
     * Methode, um den dunklen Modus zu setzen.
     */
    public void SetDarkmode() {
        jpanel1.setBackground(Color.black);
        calculate.setForeground(Color.white);
        calculate.setBackground(Color.black);
        radiusTextField.setForeground(Color.white);
        radiusTextField.setBackground(Color.black);
        heightTextField.setForeground(Color.white);
        heightTextField.setBackground(Color.black);
        radius.setForeground(Color.white);
        radius.setBackground(Color.black);
        height.setForeground(Color.white);
        height.setBackground(Color.black);
        volume.setForeground(Color.white);
        volume.setBackground(Color.black);
        volumeCalculate.setForeground(Color.white);
        volumeCalculate.setBackground(Color.black);
        spacer1.setForeground(Color.white);
        spacer1.setBackground(Color.black);
        spacer2.setForeground(Color.white);
        spacer2.setBackground(Color.black);
    }

    public JButton getCalculate() {
        return calculate;
    }

    public void Calculate() {
        double volumeRadius = Double.parseDouble(radiusTextField.getText().replace(',','.'));
        double volumeHeight = Double.parseDouble(heightTextField.getText().replace(',','.'));
        if (volumeRadius <= 0 || volumeHeight <= 0) {
            volumeCalculate.setText("ungültige Eingabe");
        }
        else {
            double volumeResult = (1.0/3.0) * Math.PI * volumeRadius * volumeRadius * volumeHeight;
            System.out.println(""+volumeResult);
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace ('.', ',');
            volumeCalculate.setText(volumeString);
            //volumeCalculate.setText("" + (double) (Math.round(volumeResult * 100)) / 100);
        }
    }
}
