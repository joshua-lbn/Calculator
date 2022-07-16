package Calculator.view.general;
import Calculator.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
    //hinzufügen des Rechners
    Controller controller = new Controller();

    public ViewNumeralSystem () {
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        JPanel.setLayout(new GridLayout(3, 3));

        //jpanel1 = new javax.swing.JPanel();
        DecLabel = new javax.swing.JLabel("   Dezimal:");
        DecTextField = new javax.swing.JTextField();
        HexaLabel = new javax.swing.JLabel("   Hexadezimal:");
        HexaTextField = new javax.swing.JTextField();
        BinLabel = new javax.swing.JLabel("   Bin\u00E4r:");
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
        setSize(600, 300);
        this.setVisible(true);


        convertDec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Feld zum Speichern des übergegeben Feldes
                String[] ConvertedNumbers = new String[2];
                //Uebergabe des Wertes an den controller
                ConvertedNumbers = controller.DectoHex_Bin(DecTextField.getText().replace(',', '.'));
                //Ausgabe des Feldes
                if (ConvertedNumbers[0] == "Fehler") {
                    DecTextField.setText("Ung\u00FCltige Eingabe");
                } else {
                    HexaTextField.setText(ConvertedNumbers[0]);
                    BinTextField.setText(ConvertedNumbers[1]);
                }
            }
        });
        convertHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String HexString = HexaTextField.getText();
                String ConvertedNumbers[] = new String[2];
                ConvertedNumbers = controller.HextoDec_Bin(HexString);
                DecTextField.setText(ConvertedNumbers[0]);
                BinTextField.setText(ConvertedNumbers[1]);
            }
        });
        convertBin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String BinString = BinTextField.getText();
                String ConvertedNumbers[] = new String[2];
                ConvertedNumbers = controller.BintoDec_Hex(BinString);
                if (ConvertedNumbers[0] == "Fehler") {
                    BinTextField.setText("Ung\u00FCltige Eingabe");
                } else {
                    DecTextField.setText(ConvertedNumbers[0]);
                    HexaTextField.setText(ConvertedNumbers[1]);
                }
            }
        });
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