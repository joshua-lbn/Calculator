package Calculator.view.viewVolume;

import javax.swing.*;
import java.awt.*;

public class viewCuboid extends JRootPane {
    private ProcessVolumeInput processVolumeInput;
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

    public viewCuboid () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jpanel1.setLayout(new GridLayout(4,3));

        //jpanel1 = new javax.swing.JPanel();
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
        //this.pack();
        this.setVisible(true);
        calculate.addActionListener(processVolumeInput);
    }
    public static void main(String[] args) {
        new Calculator.view.volumeCuboid().setVisible(true);
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        jpanel1.setBackground(Color.white);
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
     * Methode, um den dunklen Modus zu setzen.
     */
    public void SetDarkmode() {
        jpanel1.setBackground(Color.black);
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
    public JButton getCalculate() {
        return calculate;
    }

    public void Calculate() {
        System.out.println("TestCuboid");
        System.out.print(lengthTextField.getText());
        double volumeLength = Double.parseDouble(lengthTextField.getText().replace(',','.'));
        double volumeWidth = Double.parseDouble(widthTextField.getText().replace(',','.'));
        double volumeHeight = Double.parseDouble(heightTextField.getText().replace(',','.'));
        if (volumeLength <= 0 || volumeWidth <= 0 || volumeHeight <= 0) {
            volumeCalculate.setText("Ung\u00FCltige Eingabe");
        }
        else {
            double volumeResult = volumeLength * volumeWidth * volumeHeight;
            double volume = (double) (Math.round(volumeResult * 100)) / 100;
            String volumeString = (Double.toString(volume)).replace ('.', ',');
            volumeCalculate.setText(volumeString);
            //volumeCalculate.setText("" + (double) (Math.round(volumeResult * 100)) / 100);
        }
    }
}
