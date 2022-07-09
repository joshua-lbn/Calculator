
package Calculator.view;

import Calculator.controller.Controller;
import Calculator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.lang.*;
import java.util.StringJoiner;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import org.json.*;


class ViewCurrency extends JFrame{

    //erstellen der GridLayouts
    private javax.swing.JPanel grid1 = new javax.swing.JPanel();
    private javax.swing.JPanel grid11 = new javax.swing.JPanel();
    private javax.swing.JPanel grid12 = new javax.swing.JPanel();

    private javax.swing.JComboBox ausgabecb = new javax.swing.JComboBox();

    private javax.swing.JComboBox eingangcb = new javax.swing.JComboBox();

    private javax.swing.JLabel label1 = new javax.swing.JLabel();
    private javax.swing.JTextField textinput = new javax.swing.JTextField();;

    private StringBuilder sb;
    private InputStream is;
    private BufferedReader rd;
    private JSONObject json;
    private String[] c;
    private String[] c1;
    private String[] c2;
    private String[] c3;






    //Labels für Währungsnamen und Währungswert



    //Eingabenfeld



    public ViewCurrency ()  {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        grid1.setLayout(new GridLayout(1, 2));
        grid11.setLayout(new GridLayout(2, 1));
        grid1.add(grid11);
        grid12.setLayout(new GridLayout(2, 1));
        grid1.add(grid12);



//-----------------------------------------------

        c = new String[4];
        StringJoiner joiner = new StringJoiner("");
        for (int i = 0;i < 4;i++) {
            char s;
            s = Test().charAt(Test().indexOf("EUR") + i + 5 );
            c[i] = String.valueOf(s);
        }
        for(int i = 0; i < c.length; i++) {
            joiner.add(c[i]);
        }
        String str = joiner.toString();
        //System.out.println("" + str + "");

        //str gibt umtauschkurs
//-----------------------------------------------
        c1 = new String[Test().length()-42];
        c2 = new String[3];
        c3 = new String[9];





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
        //System.out.println("" + str1 + "");

        //System.out.println(Test());

        for (int n = 0; n < c3.length; n++) {
            StringJoiner joiner2 = new StringJoiner("");
            for (int i = 0; i < c2.length; i++) {
                char p;

                //if (Integer.valueOf(str1.charAt(str1.indexOf("\"") + 4)) == Integer.valueOf(str1.charAt(str1.indexOf("\"")))) {
                    p = str1.charAt(str1.indexOf("\"") + i + 1);
                    c2[i] = String.valueOf(p);
                //System.out.println(str1.indexOf("\""));
                //System.out.println(str1.charAt(str1.indexOf("\"") + i + 1));


                //}
            }
            for (int i = 0; i < c2.length; i++) {
                joiner2.add(c2[i]);
            }
            String str2 = joiner2.toString();
            //System.out.println("" + str2 + "");

            c3[n] = str2;

            int Position = str1.indexOf("\"");
            str1 = str1.substring(0,0) + str1.substring(Position+6);

            //System.out.println("" + str1 + "");
        }
        for (int e = 0; e < c3.length; e++) {
            //System.out.println("" + c3[e] + "");

        }

        //c3 gibt Währungen

//-----------------------------------------------

        eingangcb = new JComboBox(c3);
        grid11.add(eingangcb);
        System.out.println("eingangcb");

        textinput = new JTextField();
        grid11.add(textinput);
        System.out.println("textinput");


        ausgabecb = new JComboBox(c3);
        grid12.add(eingangcb);
        System.out.println("eingangcb");

        label1 = new JLabel();
        grid12.add(label1);
        System.out.println("label1");

























        this.getContentPane().add(grid1);
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
        //System.out.println(json.toString());


        return json.toString();

    }
    public static void main(String[] args) {


        new ViewCurrency().setVisible(true);


    }



}