package Calculator.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Scanner;

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
                //@Maths.abs() prüft, ob Zahl negativ, wenn ja: Betrag der Zahl wird genommen
                // (bei Hexa und Binär nicht klar, ob negative Zahlen, Martin?)
                //sont einfach löschen
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
    //too easy @Louis!
    //evtl. woanders Mehtoden @Martin
    //einfach ein "-" vor Hexaausgabe, wenn anderes negativ


}
