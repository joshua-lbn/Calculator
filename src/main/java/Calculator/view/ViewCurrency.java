
package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.lang.*;
import java.util.Objects;
import java.util.StringJoiner;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import org.json.*;


class ViewCurrency extends JFrame{



    private StringBuilder sb;
    private InputStream is;
    private BufferedReader rd;
    private JSONObject json;
    private String[] c;
    private String[] c01;
    private String[] c1;
    private String[] c2;
    private String[] c3;
    private String[] c31;


    private javax.swing.JComboBox ausgabecb = new javax.swing.JComboBox();

    private javax.swing.JComboBox eingangcb = new javax.swing.JComboBox();

    private javax.swing.JLabel label1 = new javax.swing.JLabel();
    private javax.swing.JTextField textinput = new javax.swing.JTextField();;


    //Labels für Währungsnamen und Währungswert




    public ViewCurrency ()  {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ButtonListener bl = new ButtonListener();

//-----------------------------------------------
        c1 = new String[Test().length()-42];
        c2 = new String[3];
        c3 = new String[32];
        c31 = new String[c3.length];



        StringJoiner joiner1 = new StringJoiner("");
        for (int i = 0; i < Test().length()-42; i++) {
            char u;
            u = Test().charAt(i + 29);
            c1[i] = String.valueOf(u);
        }
        for (int i = 0; i < Test().length()-43; i++) {
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

        //c3, c31 gibt Währungen(Namen)

//-----------------------------------------------




        eingangcb = new JComboBox(c3);
        eingangcb.setEditable( true );
        this.getContentPane().add( eingangcb, BorderLayout.WEST );

        ausgabecb = new JComboBox(c31);
        ausgabecb.setEditable( true );
        this.getContentPane().add( ausgabecb, BorderLayout.EAST );

        textinput = new JTextField();

        textinput.addActionListener( bl );
        label1 = new JLabel("Umgerechnete Währung");

        this.getContentPane().add(textinput, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(label1, BorderLayout.CENTER);



        // Fenster als dynamisch skalierbar definieren
        pack();
        // Groesse setzen
        setSize(600,300);
        // Sichtbar setzen
        setVisible(true);


    }


    private static String readAll(Reader rd) {
        StringBuilder sb = new StringBuilder();
        int cp;/*w w  w .j  ava2 s .co  m*/
        while (true) {
            try {
                if (!((cp = rd.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String Test() {

        JSONObject json = null;
        json = readJsonFromUrl("https://www.currency-api.com/rates?base=USD");


        return json.toString();

    }
    public static void main(String[] args) {


        new ViewCurrency().setVisible(true);


    }
    class ButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent e) {

            // Noch prüfen Buchstaben + leere Eingabe
            // Parse Double, da nicht möglich String direkt in Zahl umwandeln
            double norm = Double.parseDouble(textinput.getText());
            if (norm < 0) {
                label1.setText("0");
            }
            else {


                //umrechnen in andere Währungen
                c = new String[4];
                double in1;
                StringJoiner joiner = new StringJoiner("");
                if(((String) Objects.requireNonNull(eingangcb.getSelectedItem())).equals("USD")){
                    in1 = 1.0;
                }
                else {
                    for (int i = 0; i < 4; i++) {
                        char s;

                        s = Test().charAt(Test().indexOf((String) Objects.requireNonNull(eingangcb.getSelectedItem())) + i + 5);
                        c[i] = String.valueOf(s);

                    }

                    for (int i = 0; i < c.length; i++) {
                        joiner.add(c[i]);
                    }
                    String str = joiner.toString();
                    in1 = Double.parseDouble(str);
                }

                c01 = new String[4];
                double in2;
                StringJoiner joiner01 = new StringJoiner("");
                if(((String) Objects.requireNonNull(ausgabecb.getSelectedItem())).equals("USD")){
                   in2 = 1.0;
                }
                else {
                    for (int i = 0; i < 4; i++) {
                        char s;

                        s = Test().charAt(Test().indexOf((String) Objects.requireNonNull(ausgabecb.getSelectedItem())) + i + 5);
                        c01[i] = String.valueOf(s);

                    }

                    for (int i = 0; i < c01.length; i++) {
                        joiner01.add(c01[i]);
                    }
                    String str01 = joiner01.toString();
                    in2 = Double.parseDouble(str01);
                }


                double dollar1 = norm * ( in2 / in1 );
                //System.out.println((double) (Math.round(dollar1 * 100)) / 100);
                label1.setText("" + (double) (Math.round(dollar1 * 100)) / 100);


            }
        }
    }



}
