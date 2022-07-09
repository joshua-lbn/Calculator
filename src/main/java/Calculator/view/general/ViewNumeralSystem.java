package Calculator.view.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNumeralSystem extends JRootPane {
    private javax.swing.JPanel JPanel = new javax.swing.JPanel();
    private javax.swing.JButton convertDec = new javax.swing.JButton();
    private javax.swing.JButton convertHex = new javax.swing.JButton();
    private javax.swing.JButton convertBin = new javax.swing.JButton();
    private javax.swing.JTextField DecTextField = new javax.swing.JTextField();
    private javax.swing.JTextField HexaTextField = new javax.swing.JTextField();
    private javax.swing.JTextField BinTextField = new javax.swing.JTextField();
    private javax.swing.JLabel DecLabel = new javax.swing.JLabel();
    private javax.swing.JLabel HexaLabel = new javax.swing.JLabel();
    private javax.swing.JLabel BinLabel = new javax.swing.JLabel();


    public ViewNumeralSystem () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        JPanel.setLayout(new GridLayout(3,3));

        //jpanel1 = new javax.swing.JPanel();
        DecLabel = new javax.swing.JLabel("   Dezimal:");
        DecTextField = new javax.swing.JTextField();
        HexaLabel = new javax.swing.JLabel("    Hexadezimal:");
        HexaTextField = new javax.swing.JTextField();
        BinLabel = new javax.swing.JLabel("   Binär:");
        BinTextField = new javax.swing.JTextField();
        convertDec = new javax.swing.JButton("=");
        convertHex = new javax.swing.JButton("=");
        convertBin = new javax.swing.JButton("=");
        //ggf. noch set Size
        JPanel.add(DecLabel);
        JPanel.add(DecTextField);

        JPanel.add(convertDec);
        JPanel.add(HexaLabel);
        JPanel.add(HexaTextField);

        JPanel.add(convertHex);
        JPanel.add(BinLabel);
        JPanel.add(BinTextField);

        JPanel.add(convertBin);
        this.getContentPane().add(JPanel);
        //pack();
        setVisible(true);
        setSize(600,300);
        this.setVisible(true);

        convertDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //hier zunächt ingnorieren
                /*
                String DecString = DecTextField.getText().replace(',','.');
                int BinInt = Integer.parseInt(DecString);
                int binary = Integer.parseInt(Integer.toBinaryString(BinInt));
                BinTextField.setText(Integer.toString(binary));

                 */
                //auch für @Joshua um das Problem mit dem Komma zu lösen
                double decimalNumber = Double.parseDouble(DecTextField.getText().replace(',', '.'));
                //Runden der eingegeben Zahl
                double decimalNumberRounded = ((double) Math.round(decimalNumber * 1));
                int decInt = (int) decimalNumberRounded;
                // @Maths.abs() prüft, ob Zahl negativ, wenn ja: Betrag der Zahl wird genommen
                // (bei Hexa und Binär nicht klar, ob negative Zahlen, Martin?)
                // sonst einfach löschen
                if (decimalNumber < 0) {
                    decInt = Math.abs(decInt);
                    BinTextField.setText("-" + Integer.toBinaryString(decInt));
                    HexaTextField.setText("-" + Integer.toHexString(decInt));
                } else {
                    //Umrechnung vom Dezimalsystem in die anderen beiden Zahlensysteme
                    BinTextField.setText(Integer.toBinaryString(decInt));
                    HexaTextField.setText(Integer.toHexString(decInt));
                }
                }
        });


        convertHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String HexNumber = HexaTextField.getText();
                //int für if-Bedingung (nicht in Hexa umgerechnet)
                if(HexNumber.indexOf("-") ==0) {
                    HexNumber = HexNumber.replace("-","");
                    int decIntNeg = Integer.parseInt(HexNumber, 16);
                    DecTextField.setText("-" + Integer.toString(decIntNeg));
                    BinTextField.setText("-" + Integer.toBinaryString(decIntNeg));
                }
                else {
                    int decInt = Integer.parseInt(HexNumber, 16);
                    DecTextField.setText(Integer.toString(decInt));
                    String binary = Integer.toBinaryString(decInt);
                    BinTextField.setText(binary);
                }
            }
        });
        convertBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String BinString = BinTextField.getText();
                int BinNumberInt = Integer.parseInt(BinString);
                if(BinNumberInt < 0)
                {
                    int decIntNeg = Integer.parseInt(BinString, 2);
                    DecTextField.setText("-"+ Integer.toString(decIntNeg));
                    HexaTextField.setText(Integer.toHexString(decIntNeg));
                }
                else {
                    int decInt = Integer.parseInt(BinString, 2);
                    DecTextField.setText(Integer.toString(decInt));
                    HexaTextField.setText(Integer.toHexString(decInt));
                }
            }
        });
    }
        //this.pack();
        //this.setVisible(true);

    public static void main(String[] args) {
        new Calculator.view.volumeCuboid().setVisible(true);
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        JPanel.setBackground(Color.white);
        DecLabel.setForeground(Color.black);
        DecLabel.setBackground(Color.white);
        HexaLabel.setForeground(Color.black);
        HexaLabel.setBackground(Color.white);
        BinLabel.setForeground(Color.black);
        BinLabel.setBackground(Color.white);
        DecTextField.setForeground(Color.black);
        DecTextField.setBackground(Color.white);
        HexaTextField.setForeground(Color.black);
        HexaTextField.setBackground(Color.white);
        BinTextField.setForeground(Color.black);
        BinTextField.setBackground(Color.white);
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
        JPanel.setBackground(Color.black);
        DecLabel.setForeground(Color.white);
        DecLabel.setBackground(Color.black);
        HexaLabel.setForeground(Color.white);
        HexaLabel.setBackground(Color.black);
        BinLabel.setForeground(Color.white);
        BinLabel.setBackground(Color.black);
        DecTextField.setForeground(Color.white);
        DecTextField.setBackground(Color.black);
        HexaTextField.setForeground(Color.white);
        HexaTextField.setBackground(Color.black);
        BinTextField.setForeground(Color.white);
        BinTextField.setBackground(Color.black);
        convertDec.setForeground(Color.white);
        convertDec.setBackground(Color.black);
        convertHex.setForeground(Color.white);
        convertHex.setBackground(Color.black);
        convertBin.setForeground(Color.white);
        convertBin.setBackground(Color.black);
    }
}