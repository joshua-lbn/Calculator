package Calculator.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewNumeralSystem extends JRootPane{
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

    public ViewNumeralSystem() {
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(NumeralViewPanel);
        //this.pack();
        //this.setVisible(true);
        convertDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //auch für @Joshua um das Problem mit dem Komma zu lösen
                double decimalNumber = Double.parseDouble(DecTextField.getText().replace(',','.'));
                //Runden der eingegeben Zahl
                double decimalNumberRounded = ((double) Math.round(decimalNumber * 1));
                int decInt = (int) decimalNumberRounded;
                // @Maths.abs() prüft, ob Zahl negativ, wenn ja: Betrag der Zahl wird genommen
                // (bei Hexa und Binär nicht klar, ob negative Zahlen, Martin?)
                // sonst einfach löschen
                if (decimalNumber < 0)
                {
                    decInt = Math.abs(decInt);
                    BinTextField.setText("-" + Integer.toBinaryString(decInt));
                    HexaTextField.setText("-" + Integer.toHexString(decInt));
                }
                else{
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
    // Evtl. woanders Methoden @Martin
    // Einfach ein "-" vor Hexaausgabe, wenn anderes negativ

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        NumeralViewPanel.setBackground(Color.white);
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
        NumeralViewPanel.setBackground(Color.black);
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