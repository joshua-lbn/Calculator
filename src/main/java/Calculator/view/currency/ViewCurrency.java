package Calculator.view.currency;

import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.StringJoiner;
import java.io.*;
import java.net.*;
import java.nio.charset.*;

import Calculator.view.main.View;
import org.json.*;

/**
 * ViewCurrencyOld-Klasse mit Waehrungsunterfenster.
 * Verwendet, um Abstraktion zwischen View-Oberklasse und den Unter-Darstellungen zu schaffen.
 */
public class ViewCurrency extends JRootPane {
    // Strings
    private String[] c;
    private String[] c01;
    private String[] c1;
    private String[] c2;
    private String[] c3;
    private String[] c31;
    // Graphische Oberflaeche
    private JComboBox eingabeCB;
    private JComboBox ausgabeCB;
    private JLabel label1;
    private JTextField textInput;
    // Eingabeverarbeitung
    private ProcessCurrencyInput processCurrencyInput;
    // JSON
    String json;
    View view;

    /**
     * Konstruktor:
     */
    public ViewCurrency (View v)  {
        view = v;
        json = view.GetCurrenciesAsString();
        // Arrays initialisieren
        c1 = new String[json.length()-42];
        c2 = new String[3];
        c3 = new String[32];
        c31 = new String[c3.length];
        // Arrays zusammenfuehren
        StringJoiner joiner1 = new StringJoiner("");
        for (int i = 0; i < json.length()-42; i++) {
            char u;
            u = json.charAt(i + 29);
            c1[i] = String.valueOf(u);
        }
        for (int i = 0; i < json.length()-43; i++) {
            joiner1.add(c1[i]);
        }
        String str1 = joiner1.toString();
        for (int n = 0; n < c3.length; n++) {
            StringJoiner joiner2 = new StringJoiner("");
            for (int i = 0; i < c2.length; i++) {
                char p;
                p = str1.charAt(str1.indexOf("\"") + i + 1);
                c2[i] = String.valueOf(p);
            }
            for (int i = 0; i < c2.length; i++) {
                joiner2.add(c2[i]);
            }
            String str2 = joiner2.toString();
            c3[n] = str2;
            int Position = str1.indexOf("\"");
            str1 = str1.substring(0,0) + str1.substring(Position+6);
        }
        for (int e = 0; e < c3.length; e++) {
            c31[e] = c3[e];
        }
        // Oberflaeche erstellen
        eingabeCB = new JComboBox(c3);
        eingabeCB.setEditable(true);
        this.getContentPane().add(eingabeCB, BorderLayout.WEST );
        ausgabeCB = new JComboBox(c31);
        ausgabeCB.setEditable(true);
        this.getContentPane().add(ausgabeCB, BorderLayout.EAST );
        textInput = new JTextField();
        processCurrencyInput = new ProcessCurrencyInput(this);
        textInput.addActionListener(processCurrencyInput);
        label1 = new JLabel("Umgerechnete W\u00E4hrung");
        this.getContentPane().add(textInput, BorderLayout.NORTH);
        this.getContentPane().add(label1, BorderLayout.CENTER);
    }

    public JTextField GetTextInput() {
        return textInput;
    }

    public double ConvertCurrency(double input) {
        return view.ConvertCurrency(input, (String) eingabeCB.getSelectedItem(), (String) ausgabeCB.getSelectedItem());
    }

    public void SetResult(double output) {
        label1.setText(Double.toString(output));
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetLightmode() {
        label1.setBackground(Color.white);
        eingabeCB.setForeground(Color.black);
        eingabeCB.setBackground(Color.white);
        ausgabeCB.setForeground(Color.black);
        ausgabeCB.setBackground(Color.white);
        textInput.setForeground(Color.black);
        textInput.setBackground(Color.white);
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     */
    public void SetDarkmode() {
        label1.setBackground(Color.black);
        eingabeCB.setForeground(Color.white);
        eingabeCB.setBackground(Color.black);
        ausgabeCB.setForeground(Color.white);
        ausgabeCB.setBackground(Color.black);
        textInput.setForeground(Color.white);
        textInput.setBackground(Color.black);
    }
}
